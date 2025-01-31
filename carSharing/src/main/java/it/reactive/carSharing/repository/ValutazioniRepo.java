package it.reactive.carSharing.repository;

import it.reactive.carSharing.model.Valutazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValutazioniRepo extends JpaRepository<Valutazioni, Integer> {
}
