package ar.edu.unlp.info.bd2.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Centre")
public class Centre {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    /*@Column
    private List<Staff> staff;*/ //TODO: Descomentar cuando exista Staff (abstract)

    public Centre() {}

    public Centre(String aName) {
        name = aName;
    }

    public void setName(String aName) {
        name = aName;
    }

    public long getId () {
        return id;
    }

    public String getName() {
        return name;
    }

    /*public addStaffs(Staff aStaffMember) {
        staff.add(aStaffMember);
    }*/

    /*public List<Staff> getStaffs() {
        return staff;
    }*/
}
