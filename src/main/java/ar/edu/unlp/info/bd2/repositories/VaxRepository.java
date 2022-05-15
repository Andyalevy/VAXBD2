package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

public class VaxRepository {

    @Autowired
    private SessionFactory sessionFactory;


    /**
     * This method will get the current session, and get the supportStaff by the given dni.
     * @param dni dni del SupportStaff
     * @return el SupportStaff con ese dni
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

    /**
     * This method will get the current session, and get a vaccine by the given
     * name.
     * 
     * @param name nombre de la vacuna
     * @return la vacuna con ese nombre
     */
    public Vaccine getVaccineByName(String name) {
        Vaccine vaccine;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            vaccine = (Vaccine) session.createQuery("FROM Vaccine WHERE Name = :name").setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return vaccine;
    }

    /**
     *
     * @param name nombre del centro de vacunación
     * @return el centro de vacunación con ese nombre
     */
    public Centre getCentreByName(String name) {
        Centre centre;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            centre = (Centre) session.createQuery("FROM Centre WHERE Name = :name").setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return centre;
    }

    /**
     *
     * @param email email del usuario
     * @return el paciente con ese email
     */
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
     *
     * @param id id de un esquema de vacunación
     * @return el esquema de vacunación con ese id
     */
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
     * @param objectToSave objeto para guardar
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
                throw new VaxException("The table do not exist"); // TODO: esto
            }
        }
    }

    /**
     * This method will update a given object.
     * If the object do not exist in the database, it will throw an exception.
     * @param objectToUpdate objeto a actualizar
     * @return objectToUpdate el objeto actualizado
     * @throws VaxException
     */
    public Object update(Object objectToUpdate) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            session.update(objectToUpdate);
        } catch (Exception e) {
            throw new VaxException("The object do not exist in the database"); // TODO: esto
        }
        return objectToUpdate;
    }
}
