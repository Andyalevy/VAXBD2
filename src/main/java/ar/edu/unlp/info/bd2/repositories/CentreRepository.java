package ar.edu.unlp.info.bd2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.Centre;

public interface CentreRepository extends CrudRepository<Centre, Long>{

    Optional<Centre> findByName(String name);

    @Query("SELECT sc FROM Shot s JOIN s.centre sc GROUP BY sc ORDER BY count(sc) DESC")
    List<Centre> findAllGroupByOrderByCountByShotDesc(Pageable pageable);

    @Query("SELECT sc FROM Staff s JOIN s.centres sc GROUP BY sc ORDER BY count(s) DESC")
    List<Centre> findAllGroupByOrderByCountByStaffDesc(Pageable pageable);

}
