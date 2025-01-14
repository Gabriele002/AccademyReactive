package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.SquadraModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DaoSquadra {
    SquadraModel create(SquadraDTO torneoDTO) throws SQLException;
    void delete(int id) throws Exception;
    Optional<SquadraModel> findById(int id) throws SQLException;
    List<SquadraModel> readAll (boolean listaGiocatori) throws SQLException;
    Optional<SquadraModel> readForName (String nome);
    List<Integer> recuperoTornei(int idSquadra) throws SQLException;
}
