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

    private Set<ObjectId> IdSquadre = new HashSet<>();

    private Set<SquadraModelMongo> squadreTorneo = new HashSet<>();

    public Set<SquadraModelMongo> getSquadreTorneo() {
        return squadreTorneo;
    }

    public void setSquadreTorneo(Set<SquadraModelMongo> squadreTorneo) {
        this.squadreTorneo = squadreTorneo;
    }

    public Object get_id() {
        return _id;
    }

    public Set<ObjectId> getIdSquadre() {
        return IdSquadre;
    }

    public void setIdSquadre(Set<ObjectId> idSquadre) {
        this.IdSquadre = idSquadre;
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


    @Override
    public String toString() {
        return "TorneoMongo{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", IdSquadre=" + IdSquadre +
                '}';
    }
}
