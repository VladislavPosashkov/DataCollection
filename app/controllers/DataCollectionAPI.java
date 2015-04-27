package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.Field;
import model.FieldValue;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.NoResultException;
import java.util.List;


public class DataCollectionAPI extends Controller {
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result saveField() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest();
        }
        Field field = Json.fromJson(json, Field.class);
        JPA.em().persist(field);
        if (field.getFieldValues() != null) {
            for (FieldValue value : field.getFieldValues()) {
                value.setField(field);
                JPA.em().persist(value);
            }
        }
        return ok();
    }

    @Transactional(readOnly = true)
    public static Result getAllFields() {
        List fieldList = JPA.em().createQuery("SELECT f from field f").getResultList();
        System.out.println("Get fields");
        return ok(Json.toJson(fieldList));
    }

    @Transactional(readOnly = true)
    public static Result getFieldWithId(Integer id) {
        try {
            System.out.println("get field with id: " + id);
            Object field = JPA.em().createQuery("SELECT f from field f where f.id = :id").setParameter("id", id).getSingleResult();
            return ok(Json.toJson(field));
        } catch (NoResultException ex) {
            return noContent();
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateField(Integer id) {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest();
        }
        Field field = Json.fromJson(json, Field.class);
        JPA.em().persist(field);
        if (field.getFieldValues() != null) {
            for (FieldValue value : field.getFieldValues()) {
                value.setField(field);
                JPA.em().persist(value);
            }
        }
        return ok();
    }

    @Transactional
    public static Result deleteField(Integer id) {
        try {
            System.out.println("delete field with id: " + id);
            Object field = JPA.em().createQuery("SELECT f from field f where f.id = :id").setParameter("id", id).getSingleResult();
            JPA.em().remove(field);
            return ok();
        } catch (NoResultException ex) {
            return noContent();
        }
    }

}
