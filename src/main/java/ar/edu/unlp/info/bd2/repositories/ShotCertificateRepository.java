package ar.edu.unlp.info.bd2.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unlp.info.bd2.model.ShotCertificate;

public interface ShotCertificateRepository extends CrudRepository<ShotCertificate, Long>{

    List<ShotCertificate> findByDateBetween(Date startDate, Date endDate);

}