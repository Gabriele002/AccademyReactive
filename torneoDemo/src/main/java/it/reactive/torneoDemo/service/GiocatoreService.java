package it.reactive.torneoDemo.service;

import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class GiocatoreService {

    @Autowired
    DaoGiocatori daoGiocatori;

    public HashSet<GiocatoreResponse> giocatore(int id) throws Exception {
        HashSet<GiocatoriModel> giocatoriModels = daoGiocatori.read(id);
        return MapperGiocatore.modelToRs(giocatoriModels);
    }

}
