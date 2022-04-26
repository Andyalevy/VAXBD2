package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staff_type", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name="Staff")
public abstract class Staff {

    @Id
    @GeneratedValue
    protected Long id;
    @Column
    protected String fullName;
    @Column
    protected String dni;
    @Column
    @ManyToMany
    protected List<Centre> centres =  new ArrayList<Centre>();

    public Staff() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Centre> getCentres() { return centres; }

    public void setCentres(List<Centre> centres) { this.centres = centres; }
}
