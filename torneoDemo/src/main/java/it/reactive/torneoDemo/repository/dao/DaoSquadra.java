package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.SquadraModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DaoSquadra {
    SquadraModel create(SquadraDTO torneoDTO) throws SQLException;
    SquadraModel read(int id) throws SQLException;
    SquadraModel update(int id, SquadraDTO torneoDTO);
    SquadraModel delete(int id) throws SQLException;
    Optional<SquadraModel> findById(int id) throws SQLException;
    List<SquadraModel> readAll (boolean listaGiocatori) throws SQLException;
    Optional<SquadraModel> readForName (String nome);
}
