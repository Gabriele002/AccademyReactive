package it.reactive.torneoDemoMongo.repository.dao;

import it.reactive.torneoDemoMongo.dto.in.TorneoDTO;
import it.reactive.torneoDemoMongo.dto.resource.SquadraResponse;
import it.reactive.torneoDemoMongo.dto.resource.TorneoResponse;
import it.reactive.torneoDemoMongo.model.SquadraModelMongo;
import it.reactive.torneoDemoMongo.model.TorneoMongo;
import it.reactive.torneoDemoMongo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemoMongo.repository.mapper.MapperTorneo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class TorneoDaoImpl {

    private static final Logger log = LoggerFactory.getLogger(TorneoDaoImpl.class);

    @Autowired
    MapperTorneo mapperTorneo;

    @Autowired
    MapperSquadra mapperSquadra;

    @Autowired
    MongoTemplate mongoTemplate;

    public TorneoMongo create(TorneoDTO torneoDTO) {
        return mongoTemplate.save(mapperTorneo.dtoToMongo(torneoDTO));
    }

    public List<TorneoMongo> findAll() {
        List<TorneoMongo> torneoMongo = mongoTemplate.findAll(TorneoMongo.class);
        return torneoMongo;
    }

    public Optional<TorneoMongo> findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, TorneoMongo.class));
    }

    public void delete(String idTorneo) {
        TorneoMongo torneoMongo = findById(idTorneo).get();
        mongoTemplate.remove(torneoMongo);
    }

    public void aggiungiSquadra(TorneoMongo torneoMongo) {
        mongoTemplate.save(torneoMongo);
    }

    public List<TorneoResponse> getAllTorneo() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("nome").exists(true)),
                Aggregation.lookup("squadre", "IdSquadre", "_id", "squadreTorneo") // 'squadreTorneo' è il campo risultato
        );

        AggregationResults<TorneoMongo> result = mongoTemplate.aggregate(aggregation, "torneo", TorneoMongo.class);
        List<TorneoMongo> torneiSquadre = result.getMappedResults();

        return torneiSquadre.stream().map(torneoMongo -> {
            return mapperTorneo.modelToResponse(torneoMongo);
        }).collect(Collectors.toList());
    }

    public SquadraModelMongo getUltimaSquadraPerTorneo(String torneoId) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("_id").is(new ObjectId(torneoId))),
                Aggregation.lookup("squadre", "IdSquadre", "_id", "squadreTorneo"),
                Aggregation.sort(Sort.by(Sort.Order.desc("createdAt"))),
                Aggregation.limit(1)
        );

        AggregationResults<SquadraModelMongo> result = mongoTemplate.aggregate(aggregation, "torneo", SquadraModelMongo.class);
        List<SquadraModelMongo> squadre = result.getMappedResults();

        return squadre.isEmpty() ? null : squadre.get(0);
    }

}

