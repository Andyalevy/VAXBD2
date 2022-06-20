package ar.edu.unlp.info.bd2.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.Vaccine;

public interface VaccineRepository extends CrudRepository<Vaccine, Long>{

    Optional<Vaccine> findByName(String name);

}