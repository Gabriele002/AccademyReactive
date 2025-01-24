package it.reactive.torneoDemoMongo.service;


import it.reactive.torneoDemoMongo.dto.in.TorneoDTO;
import it.reactive.torneoDemoMongo.dto.resource.TorneoResponse;
import it.reactive.torneoDemoMongo.exception.CodiceErrori;
import it.reactive.torneoDemoMongo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemoMongo.exception.TorneoNonTrovatoException;
import it.reactive.torneoDemoMongo.model.SquadraModelMongo;
import it.reactive.torneoDemoMongo.model.TorneoMongo;
import it.reactive.torneoDemoMongo.repository.dao.SquadraDaoImpl;
import it.reactive.torneoDemoMongo.repository.dao.TorneoDaoImpl;
import it.reactive.torneoDemoMongo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemoMongo.repository.mapper.MapperTorneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TorneoService {

    @Autowired
    TrasferimentiService trasferimentiService;

    @Autowired
    TorneoDaoImpl torneoDao;

    @Autowired
    SquadraDaoImpl daoSquadra;

    @Autowired
    MapperTorneo mapperTorneo;

    @Autowired
    MapperSquadra mapperSquadra;


    public TorneoResponse createTorneo(TorneoDTO torneoDTO) throws SQLException {
        TorneoMongo torneoModel = torneoDao.create(torneoDTO);
        return mapperTorneo.modelToResponse(torneoModel);
    }


    public TorneoResponse aggiungoSquadra(String idTorneo, String idSquadra) throws SQLException {

        SquadraModelMongo squadraModel = daoSquadra.findById(idSquadra)
                .orElseThrow(() -> new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE));
        TorneoMongo torneoModelFind = torneoDao.findById(idTorneo).orElseThrow(() ->
                new TorneoNonTrovatoException(CodiceErrori.ERRORE_TORNERONONTROVATO));

        torneoModelFind.getIdSquadre().add(squadraModel.get_id());
        torneoDao.aggiungiSquadra(torneoModelFind);
        SquadraModelMongo squadraModelMongo = torneoDao.getUltimaSquadraPerTorneo(idTorneo);
        torneoModelFind.getSquadreTorneo().add(squadraModelMongo);
        TorneoResponse torneoResponse = mapperTorneo.modelToResponse(torneoModelFind);
        return torneoResponse;
    }


    public List<TorneoResponse> getAllTorneo() {
        List<TorneoResponse> torneoResponses = torneoDao.getAllTorneo();
        torneoResponses.forEach(tr -> {
            tr.getSquadre().forEach(squadraResponse -> {
                squadraResponse.getGiocatori().forEach(g -> {
                    String nomeGiocatore = g.getNomeCognome();
                    g.setTrasferimenti(trasferimentiService.trasferimenti(nomeGiocatore));
                });
            });
        });
        return torneoResponses;
    }

    public void removeTorneo(String idTorneo) throws Exception {
        TorneoMongo torneoModel = torneoDao.findById(idTorneo)
                .orElseThrow(() -> new TorneoNonTrovatoException(CodiceErrori.ERRORE_TORNERONONTROVATO));
        torneoDao.delete(idTorneo);
    }

}
