package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VaccinationSchedule")
public class VaccinationSchedule {

    @Id
    @GeneratedValue
    private Long id;
    private List<Vaccine> schedule;

    public VaccinationSchedule(Long id, List<Vaccine> schedule) {
        this.id = id;
        this.schedule = schedule;
    }

    public VaccinationSchedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Vaccine> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Vaccine> schedule) {
        this.schedule = schedule;
    }
}
