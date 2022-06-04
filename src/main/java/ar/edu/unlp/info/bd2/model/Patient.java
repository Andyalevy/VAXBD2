package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String fullname;
    private String password;
    private Date dayOfBirth;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shot> shots = new ArrayList<>();

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

    public Long getId() {
        return id;
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
