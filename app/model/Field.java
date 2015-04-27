package model;


import javax.persistence.*;
import java.util.Set;


@Entity(name = "field")
@SequenceGenerator(name = "field_generator", sequenceName = "field_field_id_seq", allocationSize = 1, initialValue = 1)
public class Field {

    private int id;
    private String label;
    private String type;
    private Boolean required;
    private Boolean isActive;
    private Set<FieldValue> fieldValues;

    public Field() {

    }

    public Field(String label, String type, Boolean required, Boolean isActive) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.isActive = isActive;
    }

    public Field(String label, String type, Boolean required, Boolean isActive, Set<FieldValue> fieldValues) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.isActive = isActive;
        this.fieldValues = fieldValues;
    }

    @Id
    @Column(name = "field_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "field_generator")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    @Column(name = "field_type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Column(name = "required")
    public Boolean getRequired() {
        return (this.required == null) ? false : this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }


    @Column(name = "is_active")
    public Boolean getIsActive() {
        return (this.isActive == null) ? false : isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    public Set<FieldValue> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (id != field.id) return false;
        if (isActive != null ? !isActive.equals(field.isActive) : field.isActive != null) return false;
        if (label != null ? !label.equals(field.label) : field.label != null) return false;
        if (required != null ? !required.equals(field.required) : field.required != null) return false;
        if (type != null ? !type.equals(field.type) : field.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (required != null ? required.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
