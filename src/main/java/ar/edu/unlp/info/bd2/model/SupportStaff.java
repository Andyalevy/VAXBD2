package ar.edu.unlp.info.bd2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("support_staff")
public class SupportStaff extends Staff {

    private String area;

    public SupportStaff() {
    }

    public SupportStaff(String dni,String fullName, String area) {
        this.setDni(dni);
        this.setFullName(fullName);
        this.setArea(area);
        this.centres = new ArrayList<>();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
