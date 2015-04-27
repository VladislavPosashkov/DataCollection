package model;

import javax.persistence.*;


@Entity(name = "response")
@SequenceGenerator(name = "response_generator", sequenceName = "response_response_id_seq", allocationSize = 1, initialValue = 1)
public class Response {
    private int id;
    private int formId;
    private int fieldId;
    private int fieldValueId;
    private String singleValue;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "response_generator")
    @Column(name = "response_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "form_id")
    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    @Basic
    @Column(name = "field_id")
    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    @Basic
    @Column(name = "field_value_id")
    public int getFieldValueId() {
        return fieldValueId;
    }

    public void setFieldValueId(int fieldValueId) {
        this.fieldValueId = fieldValueId;
    }

    @Basic
    @Column(name = "single_value")
    public String getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(String singleValue) {
        this.singleValue = singleValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (fieldId != response.fieldId) return false;
        if (fieldValueId != response.fieldValueId) return false;
        if (formId != response.formId) return false;
        if (id != response.id) return false;
        if (singleValue != null ? !singleValue.equals(response.singleValue) : response.singleValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + formId;
        result = 31 * result + fieldId;
        result = 31 * result + fieldValueId;
        result = 31 * result + (singleValue != null ? singleValue.hashCode() : 0);
        return result;
    }
}
