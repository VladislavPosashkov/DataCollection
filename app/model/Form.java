package model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "form")
@SequenceGenerator(name = "form_generator", sequenceName = "form_id_seq", allocationSize = 1, initialValue = 1)
public class Form {

    private int id;
    private List<Response> responses;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_generator")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "form_id")
    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responseList) {
        this.responses = responseList;
    }
}
