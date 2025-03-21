package it.reactive.torneoDemo.repository.mapper;

import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.dto.resource.TifoseriaResponse;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MapperSquadra {

    public static SquadraModel rsToModelWithTifoseria(ResultSet rs) throws SQLException {
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

    public static SquadraModel rsToModel(ResultSet rs) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(rs.getInt("id"));
        squadraModel.setNome(rs.getString("nome"));
        squadraModel.setColoriSociali(rs.getString("colori_sociali"));
        return squadraModel;
    }

    public static SquadraResponse modelToResponse(SquadraModel squadraModel, boolean listaGiocatori) {
        SquadraResponse squadraResponse = new SquadraResponse();
        squadraResponse.setNome(squadraModel.getNome());
        squadraResponse.setColoriSociali(squadraModel.getColoriSociali());
        squadraResponse.setIdSquadra(squadraModel.getIdSquadra());
        if (squadraModel.getTifoseria() != null) {
            TifoseriaResponse tifoseriaResponse = new TifoseriaResponse();
            tifoseriaResponse.setNomeTifoseria(squadraModel.getTifoseria().getNomeTifoseria());
            tifoseriaResponse.setSquadra(squadraModel.getNome());
            tifoseriaResponse.setIdTifoseria(squadraModel.getTifoseria().getIdTifoseria());
            squadraResponse.setTifoseria(tifoseriaResponse);
        }
        Set<GiocatoreResponse> giocatoriResponseList = new HashSet<>();
        if (listaGiocatori) {
            if (squadraModel.getGiocatori() != null && !squadraModel.getGiocatori().isEmpty()) {
                for (GiocatoriModel giocatoriModel : squadraModel.getGiocatori()) {
                    GiocatoreResponse giocatoreResponse = MapperGiocatore.modelToResponse(giocatoriModel);
                    giocatoriResponseList.add(giocatoreResponse);
                }
            }
        }
        squadraResponse.setGiocatori(giocatoriResponseList);
        return squadraResponse;
    }

    public static SquadraResponse modelToResponse(SquadraModel squadraModel) {
        SquadraResponse squadraResponse = new SquadraResponse();
        squadraResponse.setNome(squadraModel.getNome());
        squadraResponse.setColoriSociali(squadraModel.getColoriSociali());
        squadraResponse.setIdSquadra(squadraModel.getIdSquadra());
        if (squadraModel.getTifoseria() != null) {
            TifoseriaResponse tifoseriaResponse = new TifoseriaResponse();
            tifoseriaResponse.setNomeTifoseria(squadraModel.getTifoseria().getNomeTifoseria());
            tifoseriaResponse.setSquadra(squadraModel.getNome());
            tifoseriaResponse.setIdTifoseria(squadraModel.getTifoseria().getIdTifoseria());
            squadraResponse.setTifoseria(tifoseriaResponse);
        }
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

    public static SquadraResponse sgToResponse(SquadraModel squadraModel, Set<GiocatoriModel> giocatoriModels) {
        SquadraResponse squadraResponse = new SquadraResponse();
        squadraResponse.setNome(squadraModel.getNome());
        squadraResponse.setColoriSociali(squadraModel.getColoriSociali());
        Set<GiocatoreResponse> giocatoreResponseHashSet = new HashSet<>();
        giocatoriModels.forEach(giocatoriModel -> {
            GiocatoreResponse giocatoreResponse = new GiocatoreResponse();
            giocatoreResponse.setNomeCognome(giocatoriModel.getNomeCognome());
            giocatoreResponse.setIdGiocatore(giocatoriModel.getIdGiocatore());
            giocatoreResponse.setNumeroAmmonizioni(0);
            giocatoreResponseHashSet.add(giocatoreResponse);
        });
        squadraResponse.setGiocatori(giocatoreResponseHashSet);
        return squadraResponse;
    }

    public static SquadraModel rsToModelIdSquadra(ResultSet rs) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(rs.getInt("id_squadra"));
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
