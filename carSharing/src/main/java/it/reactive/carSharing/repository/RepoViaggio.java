package it.reactive.carSharing.repository;

import it.reactive.carSharing.model.Viaggi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoViaggio extends JpaRepository<Viaggi, Integer> {
}
