package model;

import javax.persistence.*;

@Entity(name = "field_value")
@SequenceGenerator(name = "field_value_generator", sequenceName = "field_value_id_seq", allocationSize = 1, initialValue = 1)
public class FieldValue {

    private int id;
    private String value;


    public FieldValue() {
    }

    @Id
    @Column(name = "id")
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
}
