package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.TorneoModel;

import java.sql.SQLException;
import java.util.Optional;

public interface DaoTorneo {
    TorneoModel create(TorneoDTO torneoDTO) throws SQLException;

    TorneoModel read(int id);

    TorneoModel update(int id, TorneoDTO torneoDTO);

    TorneoModel delete(int id);

    Optional<TorneoModel> findById(int id) throws SQLException;

    void aggiungoSquadraAlTorneo (int idSquadra, int idTorneo) throws SQLException;
}
