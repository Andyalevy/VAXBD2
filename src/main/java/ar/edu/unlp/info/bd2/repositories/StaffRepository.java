package ar.edu.unlp.info.bd2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long>{

    List<Staff> findByFullNameContaining(String name);

    
}