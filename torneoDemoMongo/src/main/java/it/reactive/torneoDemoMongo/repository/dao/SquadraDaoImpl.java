package it.reactive.torneoDemoMongo.repository.dao;

import it.reactive.torneoDemoMongo.dto.in.SquadraDTO;
import it.reactive.torneoDemoMongo.dto.in.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemoMongo.model.SquadraModelMongo;
import it.reactive.torneoDemoMongo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemoMongo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class SquadraDaoImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MapperSquadra mapperSquadra;


    public SquadraModelMongo creaSquadra(SquadraDTO squadraDTO) {
        squadraDTO.setNome(Utility.formattaStringaPerDb(squadraDTO.getNome()));
        SquadraModelMongo squadraModelMongo = mapperSquadra.dtotoMongo(squadraDTO);
        return mongoTemplate.insert(squadraModelMongo);
    }

    public SquadraModelMongo creaSquadraModel(SquadraModelMongo squadraModelMongo) {
        return mongoTemplate.save(squadraModelMongo);
    }


    public Optional<SquadraModelMongo> findByNome(String nomeSquadra) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(Utility.formattaStringaPerDb(nomeSquadra)));
        return Optional.ofNullable(mongoTemplate.findOne(query, SquadraModelMongo.class));
    }


    public Optional<SquadraModelMongo> findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, SquadraModelMongo.class));
    }

    public void deleteSquadra(String id) {
        SquadraModelMongo squadraModelMongo = findById(id).get();
        mongoTemplate.remove(squadraModelMongo);
    }

    public List<SquadraModelMongo> findAll(boolean giocatori) {
        List<SquadraModelMongo> squadraModelMongos = mongoTemplate.findAll(SquadraModelMongo.class);
        if (!giocatori) {
            squadraModelMongos.forEach(squadraModelMongo -> squadraModelMongo.setGiocatori(new HashSet<>()));
        }
        return squadraModelMongos;
    }

    public SquadraModelMongo creaSquadraConGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) {
        SquadraModelMongo squadraModelMongo = new SquadraModelMongo();
        squadraModelMongo.setGiocatori(squadreDiGiocatoriDTO.getListaGiocatori());
        squadraModelMongo.setNome(squadreDiGiocatoriDTO.getNome());
        squadraModelMongo.setColoriSociali(squadreDiGiocatoriDTO.getColoriSociali());
        return mongoTemplate.save(squadraModelMongo);
    }

}