package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staff_type", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name="staff")
public abstract class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String fullName;
    protected String dni;
    @ManyToMany(cascade = {CascadeType.ALL}) //TODO: checkear que estrategia de cascada usar
    protected List<Centre> centres =  new ArrayList<>();

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

    public void addCentre(Centre centre){
        this.centres.add(centre);
    }
}
