package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="centre")
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @ManyToMany(mappedBy = "centres", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Staff> staffs = new ArrayList<>();

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