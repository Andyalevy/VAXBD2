package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Shot")
public class Shot {
    
    @Id
    @GeneratedValue
    private Long Id;
}
