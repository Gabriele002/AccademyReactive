package it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa;

import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepoTorneoJpa extends JpaRepository<TorneoModel, Integer> {
    Optional<SquadraModel> findByNomeTorneo(String nome);


}
