package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public class VaxRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * This method will get the current session, and get the supportStaff by the given dni.
     * @param dni
     * @return Optional SupportStaff
     */
    public SupportStaff getSupportStaffByDni(String dni) {
        SupportStaff supportStaff;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            supportStaff = (SupportStaff) session.createQuery("FROM SupportStaff WHERE Dni = :dni").setParameter("dni", dni).uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return supportStaff;
    }

    public Centre getCentreByName(String name) {
        Centre centre;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            centre = (Centre) session.createQuery("FROM Centre WHERE Name = :name").setParameter("name", name).uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return centre;
    }

    public Patient getPatientByEmail(String email){
        Patient patient;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            patient = (Patient) session.createQuery("FROM Patient WHERE Email = :email").setParameter("email", email).uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return patient;
    }

    /**
     * This method will get the current session, and get a vaccine by the given name.
     * @param name
     * @return Optional Vaccine
     */
    public Vaccine getVaccineByName(String name) {
        Vaccine vaccine;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            vaccine = (Vaccine) session.createQuery("FROM Vaccine WHERE Name = :name").setParameter("name", name).uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return vaccine;
    }

    public VaccinationSchedule getVaccinationScheduleById(Long id) {
        VaccinationSchedule vaccinationSchedule;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            vaccinationSchedule = (VaccinationSchedule) session.createQuery("FROM VaccinationSchedule WHERE Id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return vaccinationSchedule;
    }

    /**
     * This method will save any given object.
     * If the table do not exist it will throw an exception.
     * @param objectToSave
     * @throws VaxException
     */
    public void save(Object objectToSave) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            session.save(objectToSave);
            session.flush();
        } catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new VaxException("Constraint Violation");
            } else {
                throw new VaxException("Exception thrown: " + e.getMessage());
            }
        }
    }

    /**
     * This method will update a given object.
     * If the object do not exist in the database, it will throw an exception.
     * @param objectToUpdate
     * @return objectToUpdate
     * @throws VaxException
     */
    public Object update(Object objectToUpdate) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            session.update(objectToUpdate);
        } catch (Exception e) {
            throw new VaxException("Exception thrown: " + e.getMessage());
        }
        return objectToUpdate;
    }

    /**
     *
     * @return el centro que más vacunas aplicó
     */
    public Centre getTopShotCentre(){
        Centre cen;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            cen= (Centre) session.createQuery("SELECT s.centre FROM Shot s INNER JOIN Centre c ON s.centre.id=c.id GROUP BY s.centre ORDER BY count(s.centre.id) DESC",Centre.class).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            throw null;
        }
        return cen;
    }
}
