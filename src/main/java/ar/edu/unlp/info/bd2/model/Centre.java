package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Centre")
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @ManyToMany
    private List<Staff> staffs = new ArrayList<Staff>();

    public Centre() {
    }

    public Centre(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getStaffs() {
        return this.staffs;
    }

    public void setStaff(List<Staff> workers) {
        this.staffs = workers;
    }

    public void addStaff(Staff worker){
        this.staffs.add(worker);
        worker.addCentre(this);
    }
    
}
