package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Vaccine")
public class Vaccine {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Vaccine(String name) {
        this.name = name;
    }

    public Vaccine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
