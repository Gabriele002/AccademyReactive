package it.reactive.torneoDemoMongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "torneo")
public class TorneoMongo {

    @Id
    private ObjectId _id;

    private String nome;

    private Set<SquadraModelMongo> squadre = new HashSet<>();

    public Object get_id() {
        return _id;
    }

    public Set<SquadraModelMongo> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraModelMongo> squadre) {
        this.squadre = squadre;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
