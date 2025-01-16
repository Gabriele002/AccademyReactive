package it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa;

import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoTorneoJpa extends JpaRepository<TorneoModel, Integer> {

    List<TorneoModel> findBySquadre_idSquadra(int idSquadra);
}
