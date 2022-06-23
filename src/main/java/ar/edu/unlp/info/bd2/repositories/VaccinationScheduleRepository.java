package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.VaccinationSchedule;

public interface VaccinationScheduleRepository extends CrudRepository<VaccinationSchedule, Long>{

    void saveAndFlush(VaccinationSchedule vaccinationSchedule);

}