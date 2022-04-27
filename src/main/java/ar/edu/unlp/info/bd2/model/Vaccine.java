package ar.edu.unlp.info.bd2.model;

import javax.persistence.Column;
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
    @Column(name = "name", unique = true)
    private String name;

    public Vaccine() {
    }

    public Vaccine(String name) {
        this.setName(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}