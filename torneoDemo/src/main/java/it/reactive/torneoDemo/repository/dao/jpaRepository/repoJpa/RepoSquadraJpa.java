package it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa;

import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepoSquadraJpa extends JpaRepository<SquadraModel, Integer> {
    Optional<SquadraModel> findByNome(String nome);

    @Modifying
    @Query(value = "delete from squadra_torneo where id_squadra = :idSquadra", nativeQuery = true)
    void deleteSquadraTorneo(@Param("idSquadra") int idSquadra);

}
