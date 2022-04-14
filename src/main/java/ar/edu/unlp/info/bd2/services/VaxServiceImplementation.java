package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.model.Nurse;
import ar.edu.unlp.info.bd2.model.SupportStaff;
import ar.edu.unlp.info.bd2.repositories.VaxException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

public class VaxServiceImplementation implements VaxService{
    @Override
    public Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException {
        return null;
    }

    @Override
    public Vaccine createVaccine(String name) throws VaxException {
        return null;
    }

    @Override
    public Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException {
        return null;
    }

    @Override
    public Optional<Patient> getPatientByEmail(String email) {
        return Optional.empty();
    }

    @Override
    @Transactional
    //Optional puede ser o no una vacuna. Si no existe ej un paciente te devuelve un optional
    public Optional<Vaccine> getVaccineByName(String name) {
        return Optional.empty();
    }

    @Override
    public Centre createCentre(String name) throws VaxException {
        return null;
    }

    @Override
    public Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException {
        return null;
    }

    @Override
    public SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException {
        return null;
    }

    @Override
    public VaccinationSchedule createVaccinationSchedule() throws VaxException {
        return null;
    }

    @Override
    public VaccinationSchedule getVaccinationScheduleById(Long id) throws VaxException {
        return null;
    }

    @Override
    public Optional<Centre> getCentreByName(String name) throws VaxException {
        return Optional.empty();
    }

    @Override
    public SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException {
        return null;
    }

    @Override
    public Centre updateCentre(Centre centre) {
        return null;
    }

    @Override
    public Optional<SupportStaff> getSupportStaffByDni(String dni) {
        return Optional.empty();
    }
}
