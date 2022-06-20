package ar.edu.unlp.info.bd2.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long>{

    Optional<Patient> findByEmail(String email);

}