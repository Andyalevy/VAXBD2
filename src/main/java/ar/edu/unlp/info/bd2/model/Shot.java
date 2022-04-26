package ar.edu.unlp.info.bd2.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Shot")
public class Shot {

    @Id
    @GeneratedValue
    private Long Id;
    private Date date;
    @ManyToOne
    private ShotCertificate shotCertificate;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Vaccine vaccine;
    @ManyToOne
    private Nurse nurse;
    @ManyToOne
    private Centre centre;

    public Shot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse){
        this.date = date;
        this.patient = patient;
        patient.addShot(this);
        this.vaccine = vaccine;
        this.nurse = nurse;
        this.centre = centre;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ShotCertificate getShotCertificate(){
        return this.shotCertificate;
    }

    public Long getId() {
        return this.Id;
    }

    public void setShotCertificate(ShotCertificate shotCertificate) {
        this.shotCertificate = shotCertificate;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Vaccine getVaccine() {
        return this.vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Nurse getNurse() {
        return this.nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Centre getCentre() {
        return this.centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}