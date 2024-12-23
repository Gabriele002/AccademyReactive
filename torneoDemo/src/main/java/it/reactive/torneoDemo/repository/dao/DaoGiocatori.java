package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface DaoGiocatori {
    GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException;
    Set<GiocatoriModel> read(int id) throws Exception;
    Optional<GiocatoriModel> readForName(String nome);

}
