package it.reactive.carSharing.repository;

import it.reactive.carSharing.model.Passeggeri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasseggeriRepo extends JpaRepository<Passeggeri, Integer> {

    Optional<Passeggeri> findByeMail(String email);

}
