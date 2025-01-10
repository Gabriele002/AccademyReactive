package it.reactive.torneoDemo.repository.mapper.rowMapper;

import it.reactive.torneoDemo.model.GiocatoriModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomRowMapperGiocatore implements RowMapper<GiocatoriModel> {
    @Override
    public GiocatoriModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setIdGiocatore(rs.getInt("id"));
        giocatoriModel.setNumeroAmmonizioni(0);
        giocatoriModel.setNomeCognome(rs.getString("nome_cognome"));
        return giocatoriModel;
    }
}
