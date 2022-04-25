package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VaccinationSchedule")
public class VaccinationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade=CascadeType.PERSIST)
	private List<Vaccine> vaccines = new ArrayList<Vaccine>();

    public VaccinationSchedule() {
    }

    public Long getId() {
        return id;
    }

    public ArrayList<Vaccine> getVaccines() {
        return (ArrayList<Vaccine>) vaccines;
    }

    public void setSchedule(ArrayList<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void addVaccine(Vaccine vaccine) {
        this.vaccines.add(vaccine);
    }
}
