package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.TorneoModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DaoTorneo {
    TorneoModel create(TorneoDTO torneoDTO) throws SQLException;
    Optional<TorneoModel> findById(int id);
    void delete(int id) throws SQLException;
    TorneoModel findByIdWithSquadre(int id) throws SQLException;
    void aggiungoSquadraAlTorneo (int idSquadra, int idTorneo) throws SQLException;
    List<TorneoModel> getAllTorneo() throws SQLException;
    List<Integer> readTorniSquadra(int idTorneo);
}
