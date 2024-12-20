package it.reactive.torneoDemo.repository.mapper;

import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MapperSquadra {

    public static SquadraModel rsToModel(ResultSet rs) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(rs.getInt("id"));
        squadraModel.setNome(rs.getString("nome"));
        squadraModel.setColoriSociali(rs.getString("colori_sociali"));
        return squadraModel;
    }

    public static SquadraResponse modelToResponse(SquadraModel squadraModel) {
        SquadraResponse squadraResponse = new SquadraResponse();
        squadraResponse.setNome(squadraModel.getNome());
        squadraResponse.setColoriSociali(squadraModel.getColoriSociali());
        squadraResponse.setIdSquadra(squadraModel.getIdSquadra());
        if (squadraModel.getGiocatori() != null && !squadraModel.getGiocatori().isEmpty()) {
            Set<GiocatoreResponse> giocatoriResponseList = new HashSet<>();
            for (GiocatoriModel giocatoriModel : squadraModel.getGiocatori()) {
                GiocatoreResponse giocatoreResponse = MapperGiocatore.modelToResponse(giocatoriModel);
                giocatoriResponseList.add(giocatoreResponse);
            }
            squadraResponse.setGiocatori(giocatoriResponseList);
        }
        return squadraResponse;
    }


    public static SquadraResponse rsToResponse(ResultSet rs) throws SQLException {
        SquadraResponse squadraResponse = new SquadraResponse();
        squadraResponse.setIdSquadra(rs.getInt("id"));
        squadraResponse.setNome(rs.getString("nome"));
        squadraResponse.setColoriSociali(rs.getString("colori_sociali"));
        return squadraResponse;
    }
}
