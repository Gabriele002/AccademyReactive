package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SquadraModel {

    @Id
    private Integer idSquadra;

    private String nome;
    private String coloriSociali;

    @OneToMany(mappedBy = "squadra")
    private Set<GiocatoriModel> giocatori = new HashSet<>();

    @OneToOne(mappedBy = "squadra")
    private TifoseriaModel tifoseria;


    @ManyToMany(mappedBy = "squadre")
    private Set<TorneoModel> tornei = new HashSet<>();

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

    public Set<GiocatoriModel> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoriModel> giocatori) {
        this.giocatori = giocatori;
    }


    public TifoseriaModel getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaModel tifoseria) {
        this.tifoseria = tifoseria;
    }

    public Set<TorneoModel> getTornei() {
        return tornei;
    }

    public void setTornei(Set<TorneoModel> tornei) {
        this.tornei = tornei;
    }
}
