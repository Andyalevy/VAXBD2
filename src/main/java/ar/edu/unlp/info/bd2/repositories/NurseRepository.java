package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, Long>{

    List<Nurse> findByExperienceGreaterThan(int years);

}