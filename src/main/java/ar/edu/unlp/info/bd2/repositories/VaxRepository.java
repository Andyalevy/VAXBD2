package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
     * This method will return a list with the nurses that have more than the given years of experience
     *
     * @param years numero de años de experienca
     * @return Lista con todos los Nurse que tengan más años de experiencia que years
     */
    public List<Nurse> getNurseWithMoreThanNYearsExperience(int years){
        List<Nurse> nurseList;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            nurseList = (List<Nurse>) session.createQuery("FROM Nurse WHERE Experience > :years").setParameter("years", years).getResultList();
        } catch (Exception e) {
            return null;
        }
        return nurseList;
    }

    /**
     * This method will get All vacine except the ones that have been applied
     * @return list of unapplied vaccines
     */
    public List<Vaccine> getUnappliedVaccines() {
        List<Vaccine> vaccineList;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            vaccineList = (List<Vaccine>) session.createQuery("FROM Vaccine v WHERE NOT EXISTS (FROM Shot s WHERE (v.id = s.vaccine))").getResultList();
        } catch (Exception e) {
            return null;
        }
        return vaccineList;
    }

    /**
     * This function will return a list of the given lenght of the Centres with most Staff
     *
     * @param n number of elements to return
     * @return list of the Centres with more staff limit by n
     */
    public List<Centre> getCentresTopNStaff(int n) {
        List<Centre> centreList;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            centreList = (List<Centre>) session.createQuery("SELECT c " +
                    "FROM Staff s JOIN s.centres c " +
                    "GROUP BY c " +
                    "ORDER BY count(s) DESC").setMaxResults(n).getResultList();
        } catch (Exception e) {
            return null;
        }
        return centreList;
    }
}