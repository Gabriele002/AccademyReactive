package it.reactive.torneoDemoMongo.mapper;

import it.reactive.torneoDemoMongo.dto.in.TorneoDTO;
import it.reactive.torneoDemoMongo.dto.resource.SquadraResponse;
import it.reactive.torneoDemoMongo.dto.resource.TorneoResponse;
import it.reactive.torneoDemoMongo.model.TorneoMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperTorneo {

    @Autowired
    MapperSquadra mapperSquadra;


    public TorneoResponse modelToResponse(TorneoMongo torneoModel) {
        TorneoResponse torneoResponse = new TorneoResponse();
        torneoResponse.setNomeTorneo(torneoModel.getNome());
        torneoResponse.setIdTorneo(String.valueOf(torneoModel.get_id()));
        if (!torneoModel.getIdSquadre().isEmpty()) {
            Set<SquadraResponse> squadraResponses =torneoModel.getSquadreTorneo().stream()
                    .map(squadraModelMongo ->
                    mapperSquadra.modelToResponse(squadraModelMongo)).collect(Collectors.toSet());
            torneoResponse.setSquadre(squadraResponses);
        }
        return torneoResponse;
    }

    public TorneoMongo dtoToMongo(TorneoDTO torneoDTO){
        TorneoMongo torneoMongo = new TorneoMongo();
        torneoMongo.setNome(torneoDTO.getNomeTorneo());
        return torneoMongo;
    }
}
