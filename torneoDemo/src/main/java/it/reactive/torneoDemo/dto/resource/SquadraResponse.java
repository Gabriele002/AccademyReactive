package it.reactive.torneoDemo.dto.resource;


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

}

