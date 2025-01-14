package it.reactive.torneoDemo.service;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.dto.resource.TorneoResponse;
import it.reactive.torneoDemo.exception.CodiceErrori;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.exception.TorneoNonTrovatoException;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.MapperTorneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TorneoService {

    @Autowired
    DaoTorneo daoTorneo;

    @Autowired
    DaoSquadra daoSquadra;

    @Autowired
    DaoGiocatori daoGiocatori;

    @Autowired
    DaoTifoseria daoTifoseria;

    @Autowired
    TrasferimentiService trasferimentiService;

    @Transactional
    public TorneoResponse createTorneo(TorneoDTO torneoDTO) throws SQLException {
        TorneoModel torneoModel = daoTorneo.create(torneoDTO);
        return MapperTorneo.modelToResponse(torneoModel);
    }


    @Transactional
    public TorneoResponse aggiungoSquadra(int idTorneo, int idSquadra) throws SQLException {

        SquadraModel squadraModel = daoSquadra.findById(idSquadra)
                .orElseThrow(() -> new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE));

        TorneoModel torneoModelFind = daoTorneo.findById(idTorneo).orElseThrow(() ->
                new TorneoNonTrovatoException(CodiceErrori.ERRORE_TORNERONONTROVATO));

        daoTorneo.aggiungoSquadraAlTorneo(idSquadra, idTorneo);
        TorneoModel torneoModel = daoTorneo.findByIdWithSquadre(idTorneo);
        Set<SquadraModel> squadraModelSet = new HashSet<>();
        squadraModelSet.add(squadraModel);
        torneoModel.setSquadre(squadraModelSet);
            torneoModel.getSquadre().forEach(s -> {
                        int idSquadraModle = s.getIdSquadra();
                        try {
                            Set<GiocatoriModel> giocatoriModels = daoGiocatori.readGiocatoriWithIdSquadra(idSquadraModle);
                            Optional<TifoseriaModel> tifoseriaModel = daoTifoseria.readForIdSquadra(idSquadraModle);
                            tifoseriaModel.ifPresent(s::setTifoseria);
                            s.setGiocatori(giocatoriModels);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

            );
        return MapperTorneo.modelToResponse(torneoModel);
    }

    public List<TorneoResponse> getAllTorneo() throws SQLException {
        List<TorneoModel> torneiSquadre = daoTorneo.getAllTorneo();
        List<TorneoResponse> torneoResponses = torneiSquadre.stream()
                .map(MapperTorneo::modelToResponse)
                .collect(Collectors.toList());

        torneoResponses.forEach(tr -> {
            Set<SquadraResponse> squadraModels = tr.getSquadre();

            squadraModels.forEach(squadraResponse -> {
                Set<GiocatoriModel> giocatori = null;
                try {
                    giocatori = daoGiocatori.readGiocatoriWithIdSquadra(squadraResponse.getIdSquadra());
                    squadraResponse.setGiocatori(MapperGiocatore.modelToRs(giocatori));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                squadraResponse.getGiocatori().forEach(g -> {
                    String nomeGiocatore = g.getNomeCognome();
                    g.setTrasferimenti(trasferimentiService.trasferimenti(nomeGiocatore));
                });
            });
        });
        return torneoResponses;
    }

    @Transactional
    public void removeTorneo(int idTorneo) throws Exception {
        TorneoModel torneoModel = daoTorneo.findById(idTorneo)
                .orElseThrow(() -> new TorneoNonTrovatoException(CodiceErrori.ERRORE_TORNERONONTROVATO));

        List<Integer> idSquadre = daoTorneo.readTorniSquadra(idTorneo);

        for (Integer idSquadra : idSquadre) {
            List<Integer> idTorneiAssociatiAllaSquadra = daoSquadra.recuperoTornei(idSquadra);
            if (idTorneiAssociatiAllaSquadra.size() == 1) {
                daoSquadra.delete(idSquadra);
            }
        }
        daoTorneo.delete(idTorneo);
    }

}
