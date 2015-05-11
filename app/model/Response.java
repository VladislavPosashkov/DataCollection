package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity(name = "response")
@SequenceGenerator(name = "response_generator", sequenceName = "response_id_seq", allocationSize = 1, initialValue = 1)
public class Response {
    private int id;
    private Form form;
    private Field field;
    private FieldValue answer;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "response_generator")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    @JsonIgnore
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @OneToOne
    @JoinColumn(name = "field_id")
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "field_value_id")
    public FieldValue getAnswer() {
        return answer;
    }

    public void setAnswer(FieldValue fieldValue) {
        this.answer = fieldValue;
    }
}
