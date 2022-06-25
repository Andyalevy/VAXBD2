package ar.edu.unlp.info.bd2.repositories;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.SupportStaff;

public interface SupportStaffRepository extends CrudRepository<SupportStaff, Long>{

    Optional<SupportStaff> findByDni(String dni);

    @Query("SELECT ss.area FROM SupportStaff ss GROUP BY ss.area ORDER BY count(ss.area) ASC")
    List<String> findLessSupportStaffArea(Pageable pageable);

}