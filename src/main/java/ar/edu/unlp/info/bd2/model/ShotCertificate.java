package ar.edu.unlp.info.bd2.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ShotCertificate")
public class ShotCertificate {

    static long ids = 0;

    @Id
    private long serialNumber = ids++;
    private Date date;
    @OneToOne
    private Shot shot;

    public ShotCertificate(){
    }

    public ShotCertificate(Date date, Shot shot){
        this.setDate(date);
        this.shot=shot;
        this.shot.setShotCertificate(this);
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
