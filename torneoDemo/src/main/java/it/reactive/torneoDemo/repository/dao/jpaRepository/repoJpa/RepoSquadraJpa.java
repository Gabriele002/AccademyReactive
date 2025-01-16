package it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa;

import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoSquadraJpa extends JpaRepository<SquadraModel, Integer> {
    Optional<SquadraModel> findByNome(String nome);


}
