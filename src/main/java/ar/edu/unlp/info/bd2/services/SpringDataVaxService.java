package ar.edu.unlp.info.bd2.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.ValidationEvent;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.CentreRepository;
import ar.edu.unlp.info.bd2.repositories.NurseRepository;
import ar.edu.unlp.info.bd2.repositories.PatientRepository;
import ar.edu.unlp.info.bd2.repositories.ShotCertificateRepository;
import ar.edu.unlp.info.bd2.repositories.ShotRepository;
import ar.edu.unlp.info.bd2.repositories.StaffRepository;
import ar.edu.unlp.info.bd2.repositories.SupportStaffRepository;
import ar.edu.unlp.info.bd2.repositories.VaccinationScheduleRepository;
import ar.edu.unlp.info.bd2.repositories.VaccineRepository;
import ar.edu.unlp.info.bd2.repositories.VaxException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class SpringDataVaxService implements VaxService{

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private CentreRepository centreRepository;
    @Autowired
    private ShotCertificateRepository shotCertificateRepository;
    @Autowired
    private VaccinationScheduleRepository vaccinationScheduleRepository;
    @Autowired
    private ShotRepository shotRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private SupportStaffRepository supportStaffRepository;

    @Override
    public List<Patient> getAllPatients() {
        return (List<Patient>) this.patientRepository.findAll();
    }

    @Override
    public List<Nurse> getNurseWithMoreThanNYearsExperience(int years) {
        return this.nurseRepository.findByExperienceGreaterThan(years);
    }

    @Override
    public List<Centre> getCentresTopNStaff(int n) {
        Pageable nPagesWithOneElement = PageRequest.of(0,n);
        return centreRepository.findAllGroupByOrderByCountByStaffDesc(nPagesWithOneElement);
    }

    @Override
    public Centre getTopShotCentre() {
        Pageable firstPageWithOneElement = PageRequest.of(0,1);
        List<Centre> centreList = this.centreRepository.findAllGroupByOrderByCountByShotDesc(firstPageWithOneElement);
        if (centreList.isEmpty()) throw null; //TODO: Consultar
        return centreList.get(0); //TODO: Consultar
    }

    @Override
    public List<Nurse> getNurseNotShot() {
        return this.nurseRepository.findAllNurseWhereShotIdIsNull();
    }

    @Override
    public String getLessEmployeesSupportStaffArea() {
        Pageable firstPageWithOneElement = PageRequest.of(0,1);
        List<String> supportStaffAreaList = this.supportStaffRepository.findLessSupportStaffArea(firstPageWithOneElement);
        if (supportStaffAreaList.isEmpty()) throw null;
        return supportStaffAreaList.get(0);
    }

    @Override
    public List<Staff> getStaffWithName(String name) {
        return (List<Staff>) this.staffRepository.findByFullNameContaining(name);
    }

    @Override
    public List<Vaccine> getUnappliedVaccines() {
        return this.vaccineRepository.findAllVaccineWhereShotIsNull();
    }

    @Override
    public List<ShotCertificate> getShotCertificatesBetweenDates(Date startDate, Date endDate) {
        return this.shotCertificateRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Patient createPatient(String email, String fullname, String password, Date dayOfBirth) throws VaxException {
        Patient patient = new Patient(email, fullname, password, dayOfBirth);
        try {
            this.patientRepository.saveAndFlush(patient);
        } catch (Exception e) { //TODO: check this
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
        return patient;
    }

    @Override
    public Vaccine createVaccine(String name) throws VaxException {
        Vaccine vax = new Vaccine(name);
        try {
            this.vaccineRepository.saveAndFlush(vax);
        } catch (Exception e) { //TODO: check this
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
        return vax;
    }

    @Override
    public Shot createShot(Patient patient, Vaccine vaccine, Date date, Centre centre, Nurse nurse) throws VaxException {
        Shot shot = new Shot(patient, vaccine, date, centre, nurse);
        this.shotRepository.save(shot);
        return shot;
    }

    @Override
    public Optional<Patient> getPatientByEmail(String email) {
        return this.patientRepository.findByEmail(email);
    }

    @Override
    public Optional<Vaccine> getVaccineByName(String name) {
        return this.vaccineRepository.findByName(name);
    }

    @Override
    public Centre createCentre(String name) throws VaxException {
        Centre centre = new Centre(name);
        this.centreRepository.save(centre);
        return centre;
    }

    @Override
    public Nurse createNurse(String dni, String fullName, Integer experience) throws VaxException {
        Nurse nurse = new Nurse(dni, fullName, experience);
        this.nurseRepository.save(nurse);
        return nurse;
    }

    @Override
    public SupportStaff createSupportStaff(String dni, String fullName, String area) throws VaxException {
        SupportStaff supportStaff = new SupportStaff(dni, fullName, area);
        this.supportStaffRepository.save(supportStaff);
        return supportStaff;
    }

    @Override
    public VaccinationSchedule createVaccinationSchedule() throws VaxException {
        VaccinationSchedule vaccinationSchedule = new VaccinationSchedule();
        this.vaccinationScheduleRepository.saveAndFlush(vaccinationSchedule);
        return vaccinationSchedule;
    }

    @Override
    public VaccinationSchedule getVaccinationScheduleById(Long id) throws VaxException {
        return this.vaccinationScheduleRepository.findById(id).get();
        // TODO: Esto en realidad esta mal porque estamos parcheando el optional. Consultar.
    }

    @Override
    public Optional<Centre> getCentreByName(String name) throws VaxException {
        return this.centreRepository.findByName(name);
    }

    @Override
    public SupportStaff updateSupportStaff(SupportStaff staff) throws VaxException {
        this.supportStaffRepository.save(staff);
        return staff;
    }

    @Override
    public Centre updateCentre(Centre centre) throws VaxException {
        this.centreRepository.save(centre);
        return centre;
    }

    @Override
    public Optional<SupportStaff> getSupportStaffByDni(String dni) {
        return this.supportStaffRepository.findByDni(dni);
    }

    @Override
    public VaccinationSchedule updateVaccinationSchedule(VaccinationSchedule schedule) throws VaxException {
        this.vaccinationScheduleRepository.save(schedule);
        return schedule;
    }
    
}
