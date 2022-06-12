package ar.edu.unlp.info.bd2.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="shot")
public class Shot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="shotCertificate_id", nullable = false)
    private ShotCertificate shotCertificate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vaccine_id", nullable = false)
    private Vaccine vaccine;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nurse_id", nullable = false)
    private Nurse nurse;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="centre_id", nullable = false)
    private Centre centre;

    public Shot() {
    }

    public Shot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse){
        this.date = date;
        this.patient = patient;
        this.vaccine = vaccine;
        this.nurse = nurse;
        this.centre = centre;
        this.setShotCertificate(new ShotCertificate(date));
        patient.addShot(this);
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
        return this.id;
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
