package ar.edu.unlp.info.bd2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shot_certificate")
public class ShotCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serial_number")
    private Long serialNumber;
    private Date date;

    public ShotCertificate(){
    }

    public ShotCertificate(Date date){
        this.setDate(date);
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

}