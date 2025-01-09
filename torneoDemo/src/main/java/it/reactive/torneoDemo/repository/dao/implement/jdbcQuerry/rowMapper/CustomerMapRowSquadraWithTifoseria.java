package it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper;

import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapRowSquadraWithTifoseria implements RowMapper<SquadraModel> {

    @Override
    public SquadraModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(rs.getInt("id"));
        squadraModel.setNome(rs.getString("nome"));
        squadraModel.setColoriSociali(rs.getString("colori_sociali"));
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setSquadra(squadraModel);
        tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
        tifoseriaModel.setIdTifoseria(rs.getInt("id_tifoseria"));
        squadraModel.setTifoseria(tifoseriaModel);
        return squadraModel;
    }
}
