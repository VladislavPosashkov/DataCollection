package model;


import javax.persistence.*;
import java.util.List;


@Entity(name = "field")
@SequenceGenerator(name = "field_generator", sequenceName = "field_id_seq", allocationSize = 1, initialValue = 1)
public class Field {

    private int id;
    private String label;
    private String type;
    private Boolean required;
    private Boolean isActive;
    private List<FieldValue> fieldValues;

    public Field() {

    }

    public Field(String label, String type, Boolean required, Boolean isActive) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.isActive = isActive;
    }

    public Field(String label, String type, Boolean required, Boolean isActive, List<FieldValue> fieldValues) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.isActive = isActive;
        this.fieldValues = fieldValues;
    }

    @Id
    @Column(name = "id")
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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "field_field_value",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "field_value_id"))
    public List<FieldValue> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
