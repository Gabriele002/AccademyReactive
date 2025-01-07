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

    public static TorneoModel rsToModelWithSquadra(ResultSet rs) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(rs.getString("nome_torneo"));
        torneoModel.setIdTorneo(rs.getInt("id"));
        Set<SquadraModel> squadraModelSet = new HashSet<>();
        while (rs.next()){
            SquadraModel squadraModel = new SquadraModel();
            squadraModel.setIdSquadra(rs.getInt("squadra_id"));
            squadraModel.setNome(rs.getString("nome_squadra"));
            squadraModel.setColoriSociali(rs.getString("colori_sociali"));
            squadraModelSet.add(squadraModel);
        };
        torneoModel.setSquadre(squadraModelSet);
        return torneoModel;
    }

    public static  TorneoModel rsToModel(ResultSet rs) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(rs.getString("nome_torneo"));
        torneoModel.setIdTorneo(rs.getInt("id"));
        return torneoModel;
    }


    public static TorneoResponse modelToResponse(TorneoModel torneoModel){
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
