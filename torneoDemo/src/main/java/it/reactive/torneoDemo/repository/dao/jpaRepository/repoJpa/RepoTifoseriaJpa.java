package it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa;

import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepoTifoseriaJpa extends JpaRepository<TifoseriaModel, Integer> {

    @Query("select t from tifoseria t where t.squadra.id = :id")
    Optional<TifoseriaModel> findByIdSquadra(@Param("id") int id);

}
