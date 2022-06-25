package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, Long>{

    List<Nurse> findByExperienceGreaterThan(int years);

    @Query("SELECT n FROM Nurse n LEFT JOIN Shot s ON n.id = s.nurse WHERE s.id IS NULL")
    List<Nurse> findAllNurseWhereShotIdIsNull();

}