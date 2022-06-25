package ar.edu.unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.Vaccine;

public interface VaccineRepository extends CrudRepository<Vaccine, Long>{

    Optional<Vaccine> findByName(String name);

    void saveAndFlush(Vaccine vax);

    @Query("SELECT v FROM Vaccine v LEFT JOIN Shot s ON v.id = s.vaccine WHERE s.id IS NULL")
    List<Vaccine> findAllVaccineWhereShotIsNull();
}