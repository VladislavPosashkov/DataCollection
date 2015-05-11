package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.*;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;


public class DataCollectionAPI extends Controller {
    @Transactional(readOnly = true)
    public static Result getFields() {
        List fieldList = JPA.em().createQuery("SELECT f from field f").getResultList();
        if (fieldList.isEmpty()) {
            return noContent();
        }
        return ok(Json.toJson(fieldList));
    }

    @Transactional(readOnly = true)
    public static Result getField(Integer id) {
        try {
            Object field = JPA.em().find(Field.class, id);
            return ok(Json.toJson(field));
        } catch (NoResultException ex) {
            return noContent();
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createField() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest();
        }

        Field field = Json.fromJson(json, Field.class);
        JPA.em().persist(field);
        return ok();
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateField(Integer id) {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest();
        }
        Field field = Json.fromJson(json, Field.class);
        JPA.em().merge(field);
        return ok();
    }

    @Transactional
    public static Result deleteField(Integer id) {
        try {
            JPA.em().createNativeQuery("DELETE FROM field_value WHERE id IN (SELECT field_value_id FROM field_field_value WHERE field_id = :id)").setParameter("id",id).executeUpdate();
            Object field = JPA.em().find(Field.class, id);
            List response = JPA.em().createQuery("select  r from response  r where r.field = :field").setParameter("field",field).getResultList();
            System.out.println(response);
            for (Object obj : response) {
                JPA.em().remove(obj);
            }
            JPA.em().remove(field);
            return ok();
        } catch (NoResultException ex) {
            return noContent();
        }
    }

    @Transactional(readOnly = true)
    public static Result getForms() {
        List formList = JPA.em().createQuery("SELECT f from form f").getResultList();
        if (formList.isEmpty()) {
            return noContent();
        }
        return ok(Json.toJson(formList));
    }


    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createForm() {
        JsonNode json = request().body().asJson();
        if (json == null || !json.isArray()) {
            return badRequest();
        }

        Form form = new Form();
        JPA.em().persist(form);

        List<Response> responses = new ArrayList<Response>();
        for (JsonNode answer : json) {
            Response response = Json.fromJson(answer, Response.class);

            FieldValue fieldValue = new FieldValue();
            if (response.getAnswer().getValue() == null) {
                Integer fieldValueId = response.getAnswer().getId();
                fieldValue = JPA.em().find(FieldValue.class, fieldValueId);
            } else {
                fieldValue.setValue(response.getAnswer().getValue());
                JPA.em().persist(fieldValue);
            }

            Integer fieldId = response.getField().getId();
            Field field = JPA.em().find(Field.class, fieldId);

            response.setField(field);
            response.setAnswer(fieldValue);

            response.setForm(form);
            JPA.em().persist(response);

            responses.add(response);
        }

        form.setResponses(responses);



        ObjectNode message = Json.newObject();
        message.put("id", form.getId());

        HeaderWebSocket.notifyListeners();
        ResponseWebSocket.notifyListeners(Json.toJson(form).toString());

        return ok();
    }

    @Transactional(readOnly = true)
    public static Result getShortFields() {

        List fieldList = JPA.em().createQuery("select f.id, f.label from field f").getResultList();
        if (fieldList.isEmpty()) {
            return noContent();
        }

        List<ObjectNode> columns = new ArrayList<>();

        for (Object aFieldList : fieldList) {
            Object[] obj = (Object[]) aFieldList;
            ObjectNode column = Json.newObject();
            column.put("id", (Integer) obj[0]);
            column.put("label", (String) obj[1]);
            columns.add(column);
        }

        return ok(Json.toJson(columns));
    }

    @Transactional(readOnly = true)
    public static Result getForm(Integer id) {
        try {
            Object field = JPA.em().find(Form.class, id);
            if (field == null) {
                return noContent();
            }
            return ok(Json.toJson(field));
        } catch (NoResultException ex) {
            return noContent();
        }
    }

    @Transactional(readOnly = true)
    public static Result getFormsCount() {
        try {
            Object count = JPA.em().createQuery("select count (f.id) from form f").getSingleResult();
            return ok(count.toString());
        } catch (NoResultException ex) {
            return ok("0");
        }
    }

    @Transactional
    public static Result deleteFieldOption(Integer id) {
        try {
            Object fieldOption = JPA.em().find(FieldValue.class, id);
            JPA.em().createNativeQuery("DELETE FROM field_field_value f WHERE f.field_value_id = :id").setParameter("id",id).executeUpdate();
            JPA.em().remove(fieldOption);
            return ok();
        } catch (NoResultException ex) {
            return noContent();
        }
    }
}
