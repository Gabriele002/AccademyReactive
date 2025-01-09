package it.reactive.torneoDemo.service;

import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.dto.resource.Trasferimenti;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class GiocatoreService {

    @Autowired
    DaoGiocatori daoGiocatori;

    @Autowired
    TrasferimentiService trasferimentiService;

    public HashSet<GiocatoreResponse> giocatore(int id) throws Exception {
        HashSet<GiocatoriModel> giocatoriModels = (HashSet<GiocatoriModel>) daoGiocatori.readGiocatoriWithIdSquadra(id);
        HashSet<GiocatoreResponse> giocatoreResponseSet = new HashSet<>();
        giocatoriModels.forEach(g -> {
            String nome = g.getNomeCognome();
            Set<Trasferimenti> trasferimentis = trasferimentiService.trasferimenti(nome);
            GiocatoreResponse giocatoreResponse = MapperGiocatore.modelToResponse(g);
            giocatoreResponse.setTrasferimenti(trasferimentis);
            giocatoreResponseSet.add(giocatoreResponse);
        });
        return giocatoreResponseSet;
    }


    public GiocatoreResponse aggiornaAmmonizioni (int id) throws Exception {
        Optional<GiocatoriModel> giocatoreModelOptional = daoGiocatori.readForId(id);
        if (giocatoreModelOptional.isPresent()){
            daoGiocatori.incrementaAmmonizioni(id);
        }
        GiocatoriModel giocatoriModel = daoGiocatori.readForId(id).
                orElseThrow(() -> new RuntimeException("Giocatore non trovato"));

        return MapperGiocatore.modelToResponse(giocatoriModel);
    }
}
