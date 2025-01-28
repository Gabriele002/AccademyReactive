package it.reactive.torneoDemoMongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "squadre")
public class SquadraModelMongo {


        @Id
        private ObjectId _id;

        private String nome;
        private String coloriSociali;
        private Set<GiocatoriModelMongo> giocatori = new HashSet<>();

        private TifoseriaModelMongo tifoseriaModelMongo;


    public TifoseriaModelMongo getTifoseriaModelMongo() {
        return tifoseriaModelMongo;
    }

    public void setTifoseriaModelMongo(TifoseriaModelMongo tifoseriaModelMongo) {
        this.tifoseriaModelMongo = tifoseriaModelMongo;
    }

    public ObjectId get_id() {
        return _id;
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

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public Set<GiocatoriModelMongo> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoriModelMongo> giocatori) {
        this.giocatori = giocatori;
    }


}
