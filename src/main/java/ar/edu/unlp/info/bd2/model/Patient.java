package ar.edu.unlp.info.bd2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Patient")
public class Patient {

    @Id
    @GeneratedValue
    private Long Id;
    @Column(unique = true)
    private String email;
    private String fullname;
    private String password;
    private Date dayOfBirth;

    @OneToOne
    private VaccinationSchedule schedule;
    @ManyToMany
    private List<Shot> shots = new ArrayList<Shot>();

    public Patient() {
    }

    public Patient(String email, String fullname, String password, Date dayOfBirth) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public VaccinationSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(VaccinationSchedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return Id;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public void setShots(List<Shot> shot) {
        this.shots = shot;
    }

    public void addShot(Shot shot){
        this.shots.add(shot);
    }
}
