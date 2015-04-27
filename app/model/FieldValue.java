package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "field_value")
@SequenceGenerator(name = "field_value_generator", sequenceName = "field_value_field_value_id_seq", allocationSize = 1, initialValue = 1)
public class FieldValue {

    private int id;
    private String value;
    private Field field;


    public FieldValue() {
    }

    public FieldValue(String value) {
        this.value = value;
    }

    @Id
    @Column(name = "field_value_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "field_value_generator")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "field_id", nullable = false)
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldValue that = (FieldValue) o;

        if (id != that.id) return false;
        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (field != null ? field.hashCode() : 0);
        return result;
    }
}
