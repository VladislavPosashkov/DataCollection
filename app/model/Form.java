package model;

import javax.persistence.*;

@Entity(name = "form")
@SequenceGenerator(name = "form_generator", sequenceName = "form_form_id_seq", allocationSize = 1, initialValue = 1)
public class Form {

    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_generator")
    @Column(name = "form_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form form = (Form) o;

        if (id != form.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
