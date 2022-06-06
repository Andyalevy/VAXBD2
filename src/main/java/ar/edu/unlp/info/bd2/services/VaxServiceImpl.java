package ar.edu.unlp.info.bd2.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.VaxException;
import ar.edu.unlp.info.bd2.repositories.VaxRepository;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class VaxServiceImpl implements VaxService{

    private VaxRepository repository;

    public VaxServiceImpl() {}

    public VaxServiceImpl(VaxRepository aRepository) {
        repository = aRepository;
    }

    @Override
    @Transactional
    public Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException {
        Patient patient = new Patient(email,fullname,password,dayOfBirth);
        this.repository.save(patient);
        return patient;
    }

    @Override
    @Transactional
    public Vaccine createVaccine(String name) throws VaxException {
        Vaccine vaccine = new Vaccine(name);
        this.repository.save(vaccine);
        return vaccine;
    }

    @Override
    @Transactional
    public Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException {
        Shot shot = new Shot(patient,vaccine,date,centre,nurse);
        this.repository.save(shot);
        return shot;
    }

    @Override
    @Rollback
    public Optional<Patient> getPatientByEmail(String email) {
        return this.repository.getPatientByEmail(email);
    }

    @Override
    @Rollback
    public Optional<Vaccine> getVaccineByName(String name) {
        return this.repository.getVaccineByName(name);
    }

    @Override
    @Transactional
    public Centre createCentre(String name) throws VaxException {
        Centre centre = new Centre(name);
        this.repository.save(centre);
        return centre;
    }

    @Override
    @Transactional
    public Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException {
        Nurse nurse = new Nurse(dni,fullName,experience);
        this.repository.save(nurse);
        return nurse;
    }

    @Override
    @Transactional
    public SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException {
        SupportStaff supportStaff = new SupportStaff(dni,fullName,area);
        this.repository.save(supportStaff);
        return supportStaff;
    }

    @Override
    @Transactional
    public VaccinationSchedule createVaccinationSchedule() throws VaxException {
        VaccinationSchedule vaccinationSchedule = new VaccinationSchedule();
        this.repository.save(vaccinationSchedule);
        return vaccinationSchedule;
    }

    @Override
    @Rollback
    public VaccinationSchedule getVaccinationScheduleById(Long id) throws VaxException {
        return this.repository.getVaccinationScheduleById(id);
    }

    @Override
    @Rollback
    public Optional<Centre> getCentreByName(String name) throws VaxException {
        return this.repository.getCentreByName(name);
    }

    @Override
    @Transactional
    public SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException {
        this.repository.update(staff);
        return staff;
    }

    @Override
    @Transactional
    public Centre updateCentre(Centre centre) throws VaxException {
        this.repository.update(centre);
        return centre;
    }

    @Override
    @Rollback
    public Optional<SupportStaff> getSupportStaffByDni(String dni) {
        return this.repository.getSupportStaffByDni(dni);
    }

    @Override
    @Transactional
    public VaccinationSchedule updateVaccinationSchedule(VaccinationSchedule vaccinationSchedule) throws VaxException {
        this.repository.update(vaccinationSchedule);
        return vaccinationSchedule;
    }

    // ---------------------- VaxStatisticsService ----------------------

    @Override
    @Rollback
    public List<Patient> getAllPatients() {
        return this.repository.getAllPatients();
    }

    @Override
    @Rollback
    public List<Nurse> getNurseWithMoreThanNYearsExperience(int years) {
        return this.repository.getNurseWithMoreThanNYearsExperience(years);
    } 

    @Override
    @Rollback
    public List<Centre> getCentresTopNStaff(int n) {
        return this.repository.getCentresTopNStaff(n);
    }

    @Override
    @Rollback
    public Centre getTopShotCentre() {
        return this.repository.getTopShotCentre();
    }

    @Override
    @Rollback
    public List<Nurse> getNurseNotShot() {
        return this.repository.getNurseNotShot();
    }

    @Override
    @Rollback
    public String getLessEmployeesSupportStaffArea() {
        return this.repository.getLessEmployeesAreaSupportStaffArea();
    }

    @Override
    @Rollback
    public List<Staff> getStaffWithName(String name) {
        return this.repository.getStaffWithName(name);
    }

    @Override
    @Rollback
    public List<Vaccine> getUnappliedVaccines() {
        return this.repository.getUnappliedVaccines();
    }

    @Override
    @Rollback
    public List<ShotCertificate> getShotCertificatesBetweenDates(Date startDate, Date endDate) {
        return this.repository.getShotCertificatesBetweenDates(startDate, endDate);
    }
}
