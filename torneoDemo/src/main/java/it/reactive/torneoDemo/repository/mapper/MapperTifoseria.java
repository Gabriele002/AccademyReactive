package it.reactive.torneoDemo.repository.mapper;

import it.reactive.torneoDemo.dto.resource.TifoseriaResponse;
import it.reactive.torneoDemo.model.TifoseriaModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperTifoseria {

    public static TifoseriaResponse modelToResponse(TifoseriaModel tifoseriaModel) {
        TifoseriaResponse tifoseriaResponse = new TifoseriaResponse();
        tifoseriaResponse.setIdTifoseria(tifoseriaModel.getIdTifoseria());
        tifoseriaResponse.setNomeTifoseria(tifoseriaModel.getNomeTifoseria());
        tifoseriaResponse.setSquadra(tifoseriaModel.getSquadra().getNome());
        return tifoseriaResponse;
    }

    public static TifoseriaModel rsToModel(ResultSet rs) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
        return tifoseriaModel;
    }
}
