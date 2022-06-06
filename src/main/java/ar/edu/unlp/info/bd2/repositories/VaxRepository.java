package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.transaction.Transactional;

@Transactional
public class VaxRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * This method will get the current session, and get the supportStaff by the given dni.
     * @param dni dni del SupportStaff
     * @return un SupportStaff con ese dni
     */
    public Optional<SupportStaff> getSupportStaffByDni(String dni) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return session.createQuery("FROM SupportStaff WHERE Dni = :dni",SupportStaff.class).setParameter("dni", dni).uniqueResultOptional();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * @param name nombre del centro
     * @return el centro que tiene ese nombre
     */
    public Optional<Centre> getCentreByName(String name) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return session.createQuery("FROM Centre WHERE Name = :name",Centre.class).setParameter("name", name).uniqueResultOptional();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * @param email correo electrónico del paciente
     * @return el paciente que tiene ese correo electrónico
     */
    public Optional<Patient> getPatientByEmail(String email){
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return session.createQuery("FROM Patient WHERE Email = :email",Patient.class).setParameter("email", email).uniqueResultOptional();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * This method will get the current session, and get a vaccine by the given name.
     * @param name nombre de la vacuna
     * @return la vacuna con ese nombre
     */
    public Optional<Vaccine> getVaccineByName(String name) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return session.createQuery("FROM Vaccine WHERE Name = :name",Vaccine.class).setParameter("name", name).uniqueResultOptional();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * @param id id del esquma de vacunación
     * @return el esquema de vacunación con ese id
     */
    public VaccinationSchedule getVaccinationScheduleById(Long id) {
        VaccinationSchedule vaccinationSchedule;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            vaccinationSchedule = session.get(VaccinationSchedule.class,id);
        } catch (Exception e) {
            return null;
        }
        return vaccinationSchedule;
    }

    /**
     * This method will save any given object.
     * If the table do not exist it will throw an exception.
     * @param objectToSave objecto a guardar
     * @throws VaxException
     */
    public void save(Object objectToSave) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
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
     * @param objectToUpdate objeto para actualizar
     * @return objectToUpdate el objeto actualizado
     * @throws VaxException
     */
    public Object update(Object objectToUpdate) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
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
    public Centre getTopShotCentre() {
        Centre cen;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            cen = (Centre) session.createQuery("SELECT s.centre FROM Shot s INNER JOIN Centre c ON s.centre.id=c.id GROUP BY s.centre ORDER BY count(s.centre.id) DESC", Centre.class).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            throw null;
        }
        return cen;
    }

    /**
     *
     * @return Los enfermeros que no aplicaron vacunas
     */
    public List<Nurse> getNurseNotShot(){
        List<Nurse> nurseList;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            nurseList= (List<Nurse>) session.createQuery("FROM Nurse Nu WHERE Nu.id NOT IN(SELECT s.nurse.id FROM Shot s)").list();
        } catch (Exception e) {
            throw null;
        }
        return nurseList;
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

    /**
     *
     * @return Lista con todos los pacientes.
     */
    public List<Patient> getAllPatients() {
        List<Patient> patientList;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            patientList = (List<Patient>) session.createQuery("FROM Patient").getResultList();
        } catch (Exception e) {
            return null;
        }
        return patientList;
    }

    /**
     *
     * @param name nombre de los/as empleados/as
     * @return enfermeros/as con ese nombre
     */
    public List<Staff> getStaffWithName(String name) {
        List<Staff> staffList;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            staffList = (List<Staff>) session.createQuery("FROM Staff s WHERE s.fullName LIKE '%" + name + "%'").getResultList();
        } catch (Exception e) {
            throw null;
        }
        return staffList;
    }

    /**
     *
     * @return Area con menos empleados de los support Staff.
     */
    public String getLessEmployeesAreaSupportStaffArea() {
        String supportStaffAreaWithLeastEmployees;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            supportStaffAreaWithLeastEmployees = (String) session.createQuery(
                    "SELECT ss.area " +
                            "FROM SupportStaff ss " +
                            "GROUP BY ss.area " +
                            "ORDER BY count(ss.area) ASC").setMaxResults(1).uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return supportStaffAreaWithLeastEmployees;
    }

    /**
     *  This method will retrieve all the shot certificates that happened between startDate and endDate.
     *
     * @param startDate
     *
     * @param endDate
     *
     * @return List of ShotCertificate which happened between startDate and endDate.
     */
    public List<ShotCertificate> getShotCertificatesBetweenDates(Date startDate, Date endDate) {
        List<ShotCertificate> shotList;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            shotList = (List<ShotCertificate>) session.createQuery(
                            "FROM ShotCertificate WHERE date BETWEEN :startDate AND :endDate")
                    .setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
        } catch (Exception e) {
            return null;
        }
        return shotList;
    }
}
