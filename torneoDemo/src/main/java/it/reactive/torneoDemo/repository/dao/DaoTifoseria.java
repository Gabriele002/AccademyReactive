package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.TifoseriaModel;

import java.sql.SQLException;
import java.util.Optional;

public interface DaoTifoseria {
    TifoseriaModel create(TifoseriaDTO tifoseriaDTO, int id) throws SQLException;
    Optional<TifoseriaModel> readForIdSquadra(int idSquadra) throws SQLException;
    TifoseriaModel update(TifoseriaDTO tifoseriaDTO, int id) throws SQLException;
    void delete(int id);
}
