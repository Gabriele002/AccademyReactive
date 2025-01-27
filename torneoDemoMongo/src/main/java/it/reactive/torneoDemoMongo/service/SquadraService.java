package it.reactive.torneoDemoMongo.service;

import it.reactive.torneoDemoMongo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemoMongo.dto.in.SquadraDTO;
import it.reactive.torneoDemoMongo.dto.in.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemoMongo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemoMongo.dto.resource.SquadraResponse;
import it.reactive.torneoDemoMongo.exception.CodiceErrori;
import it.reactive.torneoDemoMongo.exception.SquadraDuplicataException;
import it.reactive.torneoDemoMongo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemoMongo.model.GiocatoriModelMongo;
import it.reactive.torneoDemoMongo.model.SquadraModelMongo;
import it.reactive.torneoDemoMongo.model.TifoseriaModelMongo;
import it.reactive.torneoDemoMongo.repository.dao.SquadraDaoImpl;
import it.reactive.torneoDemoMongo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemoMongo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemoMongo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class SquadraService {


    @Autowired
    SquadraDaoImpl squadraDaoImpl;

    @Autowired
    MapperSquadra mapperSquadra;

    @Autowired
    MapperGiocatore giocatoreMapper;

    @Transactional
    public SquadraResponse createSquadra(SquadraDTO squadraDTO) throws Exception {
        String nomeSquadra = Utility.formattaStringaPerDb(squadraDTO.getNome());
        Optional<SquadraModelMongo> squadraModelOptional = squadraDaoImpl.findByNome(nomeSquadra);
        if (squadraModelOptional.isPresent()) {
            throw new SquadraDuplicataException(CodiceErrori.ERRORE_SQUADRADUPLICATA);
        } else {
            SquadraModelMongo createSquadra = squadraDaoImpl.creaSquadra(squadraDTO);
            return mapperSquadra.modelToResponse(createSquadra);
        }
    }


    public void deleteSquadra(String id) throws Exception {
        Optional<SquadraModelMongo> squadraModel = squadraDaoImpl.findById(id);
        if (squadraModel.isPresent()) {
            squadraDaoImpl.deleteSquadra(id);
        } else {
            throw new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE);
        }
    }


    public List<SquadraResponse> returnSquadre(boolean giocatori) throws SQLException {
        List<SquadraModelMongo> squadraModelList = squadraDaoImpl.findAll(giocatori);
        List<SquadraResponse> squadraResponses = new ArrayList<>();
        for (SquadraModelMongo squadraModel : squadraModelList) {
            SquadraResponse squadraResponse = mapperSquadra.modelToResponse(squadraModel);
            if (squadraResponse.getGiocatori() == null) {
                squadraResponse.setGiocatori(new HashSet<>());
            }
            squadraResponses.add(squadraResponse);
        }
        return squadraResponses;
    }


    public SquadraResponse aggiungiGiocatore(GiocatoreDTO giocatoreDTO, String id) throws Exception {
        Optional<SquadraModelMongo> squadraModel = squadraDaoImpl.findById(id);
        if (squadraModel.isPresent()) {
            SquadraModelMongo squadraModelMongo = squadraModel.get();
            Set<GiocatoriModelMongo> giocatoriModelMongos = squadraModelMongo.getGiocatori();
            giocatoriModelMongos.add(giocatoreMapper.dtoToModel(giocatoreDTO));
            squadraModelMongo.setGiocatori(giocatoriModelMongos);
            squadraDaoImpl.creaSquadraModel(squadraModelMongo);
        } else {
            throw new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE);
        }
        return mapperSquadra.modelToResponse(squadraModel.get());
    }

    public SquadraResponse aggiungiTifoseria(TifoseriaDTO tifoseriaDTO, String id) throws Exception {
        SquadraModelMongo squadraModel = squadraDaoImpl.findById(id).orElseThrow(() -> new SquadraDuplicataException(CodiceErrori.ERRORE_SQUADRADUPLICATA));
        TifoseriaModelMongo tifoseriaModelMongo = new TifoseriaModelMongo();
        tifoseriaModelMongo.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        squadraModel.setTifoseriaModelMongo(tifoseriaModelMongo);
        squadraDaoImpl.creaSquadraModel(squadraModel);
        return mapperSquadra.modelToResponse(squadraModel);
    }

    public SquadraResponse aggiungoSquadraGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) throws SQLException {
        SquadraDTO squadraDTO = new SquadraDTO(squadreDiGiocatoriDTO.getNome(), squadreDiGiocatoriDTO.getColoriSociali());
        Optional<SquadraModelMongo> squadraModelMongoOptional = squadraDaoImpl.findByNome(Utility.formattaStringaPerDb(squadreDiGiocatoriDTO.getNome()));
        if (squadraModelMongoOptional.isPresent()) {
            throw new SquadraDuplicataException(CodiceErrori.ERRORE_SQUADRADUPLICATA);
        }
        SquadraModelMongo squadraModelMongo = mapperSquadra.dtotoMongo(squadraDTO);
        Set<GiocatoriModelMongo> giocatoriModels = squadreDiGiocatoriDTO.getListaGiocatori();
        squadraModelMongo.setGiocatori(giocatoriModels);
        squadraModelMongo = squadraDaoImpl.creaSquadraConGiocatori(squadreDiGiocatoriDTO);
        return mapperSquadra.modelToResponse(squadraModelMongo);
    }
}





