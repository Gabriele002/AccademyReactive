package it.reactive.torneoDemoMongo.mapper;

import it.reactive.torneoDemoMongo.dto.in.SquadraDTO;
import it.reactive.torneoDemoMongo.dto.resource.SquadraResponse;
import it.reactive.torneoDemoMongo.model.SquadraModelMongo;
import it.reactive.torneoDemoMongo.model.TifoseriaModelMongo;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class MapperSquadra {



    public SquadraModelMongo dtotoMongo(SquadraDTO squadraDTO) {
        SquadraModelMongo squadraMongo = new SquadraModelMongo();
        squadraMongo.setNome(squadraDTO.getNome());
        squadraMongo.setColoriSociali(squadraDTO.getColoriSociali());
        return squadraMongo;
    }

    public SquadraResponse modelToResponse(SquadraModelMongo squadraModelMongo) {
        SquadraResponse squadraResponse = new SquadraResponse();
        if (squadraModelMongo.getGiocatori().isEmpty()){
            squadraResponse.setGiocatori(new HashSet<>());
        } else {
            squadraResponse.setGiocatori(new HashSet<>(squadraModelMongo.getGiocatori()));
        }
        if (squadraModelMongo.getTifoseriaModelMongo() == null){
            squadraResponse.setTifoseria(new TifoseriaModelMongo());
        } else{
            TifoseriaModelMongo tifoseriaModelMongo = new TifoseriaModelMongo();
            tifoseriaModelMongo.setNomeTifoseria(squadraModelMongo.getTifoseriaModelMongo().getNomeTifoseria());
            squadraResponse.setTifoseria(tifoseriaModelMongo);
        }
        squadraResponse.setColoriSociali(squadraModelMongo.getColoriSociali());
        squadraResponse.setNome(squadraModelMongo.getNome());
        squadraResponse.setIdSquadra(String.valueOf(squadraModelMongo.get_id()));
        return squadraResponse;
    }


}
