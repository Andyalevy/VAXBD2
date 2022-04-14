package ar.edu.unlp.info.bd2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Nurse")
public class Nurse extends Staff {
    protected Integer experience;

    public Nurse() {
    }

    public Nurse(String fullName, String dni, Integer experience) {
        this.setDni(dni);
        this.setFullName(fullName);
        this.setExperience(experience);
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
