package it.reactive.carSharing.repository;

import it.reactive.carSharing.model.Autisti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutistaRepo extends JpaRepository<Autisti, Integer> {

    Optional<Autisti> findByeMail(String email);

}
