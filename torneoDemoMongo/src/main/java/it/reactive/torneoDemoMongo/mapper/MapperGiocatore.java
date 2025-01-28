package it.reactive.torneoDemoMongo.mapper;

import it.reactive.torneoDemoMongo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemoMongo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemoMongo.model.GiocatoriModelMongo;
import it.reactive.torneoDemoMongo.utility.Utility;
import org.springframework.stereotype.Component;


@Component
public class MapperGiocatore {

    public GiocatoriModelMongo dtoToModel(GiocatoreDTO giocatoreDTO){
        GiocatoriModelMongo giocatoriModelMongo = new GiocatoriModelMongo();
        giocatoriModelMongo.setNomeCognome(Utility.formattaStringaPerDb(giocatoreDTO.getNomeCognome()));
        giocatoriModelMongo.setNumeroAmmonizione(0);
        return giocatoriModelMongo;
    }

    public GiocatoreResponse modelToResponse(GiocatoriModelMongo giocatoreModel){
        GiocatoreResponse giocatoreResponse = new GiocatoreResponse();
        giocatoreResponse.setNomeCognome(Utility.formattaStringaPerDb(giocatoreModel.getNomeCognome()));
        giocatoreResponse.setNumeroAmmonizioni(giocatoreModel.getNumeroAmmonizione());
        giocatoreResponse.setTrasferimenti(giocatoreModel.getTrasferimenti());
        return giocatoreResponse;
    }
}
