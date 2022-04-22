package ar.edu.unlp.info.bd2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="SupportStaff")
@DiscriminatorValue("SupportStaff")
public class SupportStaff extends Staff {

    protected String area;

    public SupportStaff() {
    }

    public SupportStaff(String fullName, String dni, String area) {
        this.setDni(dni);
        this.setFullName(fullName);
        this.setArea(area);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
