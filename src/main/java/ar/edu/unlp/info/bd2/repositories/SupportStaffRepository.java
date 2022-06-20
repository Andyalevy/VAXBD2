package ar.edu.unlp.info.bd2.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.SupportStaff;

public interface SupportStaffRepository extends CrudRepository<SupportStaff, Long>{

    Optional<SupportStaff> findByDni(String dni);

}