package it.reactive.torneoDemo.service;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.dto.resource.TifoseriaResponse;
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
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperTifoseria;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SquadraService {

    @Autowired
    DaoSquadra daoSquadra;

    @Autowired
    DaoGiocatori daoGiocatori;

    @Autowired
    DaoTifoseria daoTifoseria;

    public SquadraResponse createSquadra(SquadraDTO squadraDTO) throws Exception {
        String nomeSquadra = Utility.formattaStringaPerDb(squadraDTO.getNome());
        Optional<SquadraModel> squadraModel = daoSquadra.readForName(nomeSquadra);
        if (squadraModel.isPresent()) {
            throw new SquadraDuplicataException(CodiceErrori.ERRORE_SQUADRADUPLICATA);
        }
        SquadraModel createSquadra = daoSquadra.create(squadraDTO);
        return MapperSquadra.modelToResponse(createSquadra);
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
            squadraResponses.add(squadraResponse);
        }
        return squadraResponses;
    }

    public SquadraResponse aggiungiGiocatore(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
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
            squadraModel.get().getGiocatori().add(giocatoriModel);
        } else {
            throw new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE);
        }
        return MapperSquadra.modelToResponse(squadraModel.get());
    }

    public SquadraResponse aggiungiTifoseria(TifoseriaDTO tifoseriaDTO, int id) throws Exception {
        Optional<SquadraModel> squadraModel = daoSquadra.findById(id);
        System.out.println("squadraModel = " + squadraModel.toString());
        TifoseriaModel tifoseriaModel;
        if (squadraModel.isPresent()) {
            SquadraModel squadra = squadraModel.get();
            tifoseriaModel = daoTifoseria.create(tifoseriaDTO, id);
            tifoseriaModel.setSquadra(squadraModel.get());
          //  tifoseriaModel = daoTifoseria.update(tifoseriaDTO);
            squadra.setGiocatori(daoGiocatori.read(id));
        } else {
            throw new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE);
        }
        SquadraResponse squadraResponse = MapperSquadra.modelToResponse(squadraModel.get());
        TifoseriaResponse tifoseriaResponse = MapperTifoseria.modelToResponse(tifoseriaModel);
        squadraResponse.setTifoseria(tifoseriaResponse);
        return squadraResponse;
    }

}
