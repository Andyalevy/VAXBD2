package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="vaccination_schedule")
public class VaccinationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OrderColumn
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Vaccine> vaccines = new ArrayList<>();

    public VaccinationSchedule() {
    }

    public Long getId() {
        return id;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setSchedule(ArrayList<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void addVaccine(Vaccine vaccine) {
        this.vaccines.add(vaccine);
    }
}
