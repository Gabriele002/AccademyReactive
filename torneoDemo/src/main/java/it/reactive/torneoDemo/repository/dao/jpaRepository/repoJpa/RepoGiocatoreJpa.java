package it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa;

import it.reactive.torneoDemo.model.GiocatoriModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface RepoGiocatoreJpa extends JpaRepository<GiocatoriModel, Integer> {
    Optional<GiocatoriModel> findByNomeCognome(String nome);

    @Query("select g from GiocatoriModel g where g.squadra.idSquadra = :id")
    Set<GiocatoriModel> findByIdSquadra(@Param("id") int id);
}
