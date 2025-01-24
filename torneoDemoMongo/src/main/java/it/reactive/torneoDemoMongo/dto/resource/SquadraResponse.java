package it.reactive.torneoDemoMongo.dto.resource;


import it.reactive.torneoDemoMongo.model.GiocatoriModelMongo;
import it.reactive.torneoDemoMongo.model.TifoseriaModelMongo;

import java.util.Objects;
import java.util.Set;

public class SquadraResponse {

    private String idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<GiocatoriModelMongo> giocatori;
    private TifoseriaModelMongo tifoseria;


    public SquadraResponse() {
    }

    public String getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(String idSquadra) {
        this.idSquadra = idSquadra;
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

    public TifoseriaModelMongo getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaModelMongo tifoseria) {
        this.tifoseria = tifoseria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquadraResponse that = (SquadraResponse) o;
        return Objects.equals(idSquadra, that.idSquadra) && Objects.equals(nome, that.nome) && Objects.equals(coloriSociali, that.coloriSociali) && Objects.equals(tifoseria, that.tifoseria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSquadra, nome, coloriSociali, tifoseria);
    }
}

