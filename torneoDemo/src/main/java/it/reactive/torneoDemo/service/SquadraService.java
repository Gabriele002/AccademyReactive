package it.reactive.torneoDemo.service;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.dto.in.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.dto.resource.TifoseriaResponse;
import it.reactive.torneoDemo.dto.resource.Trasferimenti;
import it.reactive.torneoDemo.exception.CodiceErrori;
import it.reactive.torneoDemo.exception.GiocatoreDuplicatoException;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperTifoseria;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class SquadraService {

    @Autowired
    DaoSquadra daoSquadra;

    @Autowired
    DaoGiocatori daoGiocatori;

    @Autowired
    DaoTifoseria daoTifoseria;

    @Autowired
    TrasferimentiService trasferimentiService;

    @Transactional
    public SquadraResponse createSquadra(SquadraDTO squadraDTO) throws Exception {
        String nomeSquadra = Utility.formattaStringaPerDb(squadraDTO.getNome());
        Optional<SquadraModel> squadraModelOptional= daoSquadra.readForName(nomeSquadra);
        if (squadraModelOptional.isPresent()){
            throw new SquadraDuplicataException(CodiceErrori.ERRORE_SQUADRADUPLICATA);
        }else {
            SquadraModel createSquadra = daoSquadra.create(squadraDTO);
            return MapperSquadra.modelToResponse(createSquadra);
        }
    }

    public void deleteSquadra(int id) throws SQLException {
        Optional<SquadraModel> squadraModel = daoSquadra.findById(id);
        if (squadraModel.isPresent()) {
            daoSquadra.delete(id);
        } else {
            throw new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE);
        }
    }

    public List<SquadraResponse> returnSquadre(boolean giocatori) throws SQLException {
        List<SquadraModel> squadraModels = daoSquadra.readAll(giocatori);
        List<SquadraResponse> squadraResponses = new ArrayList<>();
        for (SquadraModel squadraModel : squadraModels) {
            SquadraResponse squadraResponse = MapperSquadra.modelToResponse(squadraModel);
            if (squadraResponse.getGiocatori() == null){
                squadraResponse.setGiocatori(new HashSet<>());
            }else {
                squadraResponse.getGiocatori().forEach(g -> {
                    String nome = g.getNomeCognome();
                    Set<Trasferimenti> trasferimenti = trasferimentiService.trasferimenti(nome);
                    g.setTrasferimenti(trasferimenti);
                });
            }
            squadraResponses.add(squadraResponse);
        }
        return squadraResponses;
    }

    @Transactional
    public SquadraResponse aggiungiGiocatore(GiocatoreDTO giocatoreDTO, int id) throws Exception {
        Optional<SquadraModel> squadraModel = daoSquadra.findById(id);
        GiocatoriModel giocatoriModel;
        if (squadraModel.isPresent()) {
            String nomeCognomeGiocatore = Utility.formattaStringaPerDb(giocatoreDTO.getNomeCognome());
            Optional<GiocatoriModel> giocatoriModelOptional = daoGiocatori.readForName(nomeCognomeGiocatore);
            if (giocatoriModelOptional.isPresent()) {
                throw new GiocatoreDuplicatoException(CodiceErrori.ERRORE_GIOCATOREDUPLICATO);
            }
            giocatoriModel = daoGiocatori.create(giocatoreDTO, id);
            giocatoriModel.setSquadra(squadraModel.get());
            Set<GiocatoriModel> giocatoriModels = daoGiocatori.readGiocatoriWithIdSquadra(id);
            squadraModel.get().setGiocatori(giocatoriModels);
            Optional<TifoseriaModel> tifoseriaModelOptional = daoTifoseria.readForIdSquadra(id);
            tifoseriaModelOptional.ifPresent(tifoseriaModel -> squadraModel.get().setTifoseria(tifoseriaModel));
        } else {
            throw new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE);
        }
        return MapperSquadra.modelToResponse(squadraModel.get());
    }

    @Transactional
    public SquadraResponse aggiungiTifoseria(TifoseriaDTO tifoseriaDTO, int id) throws Exception {
        Optional<SquadraModel> squadraModel = daoSquadra.findById(id);
        if (squadraModel.isPresent()) {
            SquadraModel squadra = squadraModel.get();
            TifoseriaModel tifoseriaModel;
            Optional<TifoseriaModel> tifoseriaModelOptional = daoTifoseria.readForIdSquadra(id);
            if (tifoseriaModelOptional.isPresent()) {
                tifoseriaModel = daoTifoseria.update(tifoseriaDTO, id);
            } else {
                tifoseriaModel = daoTifoseria.create(tifoseriaDTO, id);
            }
            tifoseriaModel.setSquadra(squadra);
            squadra.setGiocatori(daoGiocatori.readGiocatoriWithIdSquadra(id));
            SquadraResponse squadraResponse = MapperSquadra.modelToResponse(squadra);
            TifoseriaResponse tifoseriaResponse = MapperTifoseria.modelToResponse(tifoseriaModel);
            squadraResponse.setTifoseria(tifoseriaResponse);
            return squadraResponse;
        } else {
            throw new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE);
        }
    }


    @Transactional
    public SquadraResponse aggiungoSquadraGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) throws SQLException {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setNome(Utility.formattaStringaPerDb(squadreDiGiocatoriDTO.getNome()));
        squadraDTO.setColoriSociali(squadreDiGiocatoriDTO.getColoriSociali());
        Optional<SquadraModel> squadraModelOptional = daoSquadra.readForName(squadraDTO.getNome());
        if (squadraModelOptional.isPresent()){
            throw new SquadraDuplicataException(CodiceErrori.ERRORE_SQUADRADUPLICATA);
        }else {
            SquadraModel squadraModel = daoSquadra.create(squadraDTO);
            Set<GiocatoriModel> giocatoriModels = new HashSet<>();
            for (GiocatoreDTO giocatoreDTO : squadreDiGiocatoriDTO.getListaGiocatori()) {
                GiocatoriModel giocatoriModel = daoGiocatori.create(giocatoreDTO, squadraModel.getIdSquadra());
                giocatoriModels.add(giocatoriModel);
            }
            return MapperSquadra.sgToResponse(squadraModel, giocatoriModels);
        }
    }

}
