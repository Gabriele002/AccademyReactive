package it.reactive.torneoDemo.repository.mapper.rowMapper;

import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CustomRowMapperTorneo implements RowMapper<TorneoModel> {
    @Override
    public TorneoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(rs.getString("nome_torneo"));
        torneoModel.setIdTorneo(rs.getInt("id"));
        Set<SquadraModel> squadraModelSet = new HashSet<>();
        while (rs.next()){
            SquadraModel squadraModel = new SquadraModel();
            squadraModel.setIdSquadra(rs.getInt("id_squadra"));
            squadraModel.setNome(rs.getString("nome"));
            squadraModel.setColoriSociali(rs.getString("colori_sociali"));
            squadraModelSet.add(squadraModel);
        };
        torneoModel.setSquadre(squadraModelSet);
        return torneoModel;
    }
}
