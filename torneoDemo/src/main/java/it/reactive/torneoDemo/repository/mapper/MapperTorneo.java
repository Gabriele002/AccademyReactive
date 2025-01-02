package it.reactive.torneoDemo.repository.mapper;

import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.dto.resource.TorneoResponse;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MapperTorneo {

    public static TorneoModel rsToModel (ResultSet rs) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(rs.getString("nome_torneo"));
        torneoModel.setIdTorneo(rs.getInt("id"));
        return torneoModel;
    }

    public static TorneoResponse modelToResponde(TorneoModel torneoModel){
        TorneoResponse torneoResponse = new TorneoResponse();
        torneoResponse.setIdTorneo(torneoModel.getIdTorneo());
        torneoResponse.setNomeTorneo(torneoModel.getNomeTorneo());
        Set<SquadraModel> squadraModelSet = torneoModel.getSquadre();
        if (squadraModelSet != null && !squadraModelSet.isEmpty()) {
            Set<SquadraResponse> squadraResponseSet = new HashSet<>();
            squadraModelSet.forEach(squadraModel -> {
                SquadraResponse squadraResponse = MapperSquadra.modelToResponse(squadraModel);
                squadraResponseSet.add(squadraResponse);
            });
            torneoResponse.setSquadre(squadraResponseSet);
        }
        return torneoResponse;
    }
}
