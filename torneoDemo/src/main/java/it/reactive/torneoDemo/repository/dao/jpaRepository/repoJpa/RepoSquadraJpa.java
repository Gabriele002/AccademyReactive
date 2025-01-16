package it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa;

import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepoSquadraJpa extends JpaRepository<SquadraModel, Integer> {
    Optional<SquadraModel> findByNome(String nome);

    @Query(nativeQuery = true, value = "select * from squadra")
    List<SquadraModel> trova();
}
