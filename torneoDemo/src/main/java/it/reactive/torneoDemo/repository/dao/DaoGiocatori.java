package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;

public interface DaoGiocatori {
    GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException;
    HashSet<GiocatoriModel> read(int id) throws Exception;
    Optional<GiocatoriModel> readForName(String nome);
    GiocatoriModel update(int id);
    GiocatoriModel delete(int id);
}
