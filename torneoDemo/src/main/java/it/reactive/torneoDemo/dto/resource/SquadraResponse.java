package it.reactive.torneoDemo.dto.resource;


import java.util.Objects;
import java.util.Set;

public class SquadraResponse {

    private Integer idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<GiocatoreResponse> giocatori;
    private TifoseriaResponse tifoseria;


    public SquadraResponse() {
    }

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
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

    public Set<GiocatoreResponse> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreResponse> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaResponse getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaResponse tifoseria) {
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

