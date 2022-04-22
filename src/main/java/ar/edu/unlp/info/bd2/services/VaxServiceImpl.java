package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.VaxException;
import ar.edu.unlp.info.bd2.repositories.VaxRepository;
//import jdk.jfr.internal.Repository;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class VaxServiceImpl implements VaxService{

    //TODO: Revisar si es correcta esta forma de tener el repositorio.
    @Autowired
    private VaxRepository repository;

    public VaxServiceImpl() {}

    public VaxServiceImpl(VaxRepository aRepository) {
        repository = aRepository;
    }

    //TODO: Descomentar todo a medida que se vaya haciendo, los imports de las lineas 3 y 4 tambien.
    /*@Override
    public Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException {
        return null;
    }*/

    /*@Override
    public Vaccine createVaccine(String name) throws VaxException {
        return null;
    }*/

    /*@Override
    public Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException {
        return null;
    }*/

    /*@Override
    public Optional<Patient> getPatientByEmail(String email) {
        return Optional.empty();
    }*/

    /*@Override
    @Transactional
    //Optional puede ser o no una vacuna. Si no existe ej un paciente te devuelve un optional
    public Optional<Vaccine> getVaccineByName(String name) {
        return Optional.empty();
    }*/

    /*@Override
    public Centre createCentre(String name) throws VaxException {
        return null;
    }*/

    /*@Override
    public Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException {
        return null;
    }*/

    @Override
    public SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException {
        SupportStaff supportStaff = new SupportStaff(dni,fullName,area);
        this.repository.save(supportStaff);
        return supportStaff;
    }

    /*@Override
    public VaccinationSchedule createVaccinationSchedule() throws VaxException {
        return null;
    }*/

    /*@Override
    public VaccinationSchedule getVaccinationScheduleById(Long id) throws VaxException {
        return null;
    }*/

    /*@Override
    public Optional<Centre> getCentreByName(String name) throws VaxException {
        return Optional.empty();
    }*/

    @Override
    public SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException {
        this.repository.update(staff);
        return staff;
    }

    /*@Override
    public Centre updateCentre(Centre centre) {
        return null;
    }*/

    @Override
    @Transactional
    @Rollback
    public Optional<SupportStaff> getSupportStaffByDni(String dni) {
        return Optional.ofNullable(this.repository.getSupportStaffByDni(dni));
    }
}
