package it.reactive.torneoDemo.repository.mapper;

import it.reactive.torneoDemo.dto.resource.TifoseriaResponse;
import it.reactive.torneoDemo.model.TifoseriaModel;

public class MapperTifoseria {

    public static TifoseriaResponse modelToResponse(TifoseriaModel tifoseriaModel) {
        TifoseriaResponse tifoseriaResponse = new TifoseriaResponse();
        tifoseriaResponse.setIdTifoseria(tifoseriaModel.getIdTifoseria());
        tifoseriaResponse.setNomeTifoseria(tifoseriaModel.getNomeTifoseria());
        tifoseriaResponse.setSquadra(tifoseriaModel.getSquadra().getNome());
        return tifoseriaResponse;
    }
}
