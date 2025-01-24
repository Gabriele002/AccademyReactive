package it.reactive.torneoDemoMongo.repository.dao;

import it.reactive.torneoDemoMongo.dto.in.TorneoDTO;
import it.reactive.torneoDemoMongo.dto.resource.TorneoResponse;
import it.reactive.torneoDemoMongo.model.SquadraModelMongo;
import it.reactive.torneoDemoMongo.model.TorneoMongo;
import it.reactive.torneoDemoMongo.repository.mapper.MapperTorneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TorneoDaoImpl {

    @Autowired
    MapperTorneo mapperTorneo;

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
                Aggregation.match(Criteria.where("nome").exists(true))
        );
        AggregationResults<TorneoMongo> result = mongoTemplate.aggregate(aggregation, "torneo", TorneoMongo.class);

        List<TorneoMongo> torneiSquadre = result.getMappedResults();

        return torneiSquadre.stream()
                .map(mapperTorneo::modelToResponse)
                .collect(Collectors.toList());
    }
}

