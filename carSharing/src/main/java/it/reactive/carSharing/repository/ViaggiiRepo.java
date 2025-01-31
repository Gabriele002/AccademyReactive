package it.reactive.carSharing.repository;

import it.reactive.carSharing.model.Viaggi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggiiRepo extends JpaRepository<Viaggi, Integer> {


}

