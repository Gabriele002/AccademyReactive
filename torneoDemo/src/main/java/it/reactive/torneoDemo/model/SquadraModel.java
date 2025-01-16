package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "squadra")
@NamedQuery(
        name = "findAll",
        query = "select s from SquadraModel s"
)
@NamedQuery(
        name = "findByName",
        query = "select s from SquadraModel s where nome = :nomeSquadra"
)
@NamedQuery(
        name = "findTornei",
        query = "select t.id from TorneoModel t join t.squadre s where s.id = :idSquadra"
)
public class SquadraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idSquadra;

    private String nome;

    private String coloriSociali;

    @OneToMany(mappedBy = "squadra", fetch = FetchType.EAGER)
    private Set<GiocatoriModel> giocatori = new HashSet<>();

    @OneToOne(mappedBy = "squadra", optional = true)
    private TifoseriaModel tifoseria;

    @ManyToMany(mappedBy = "squadre")
    private Set<TorneoModel> tornei = new HashSet<>();

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

    public Set<TorneoModel> getTornei() {
        return tornei;
    }

    public void setTornei(Set<TorneoModel> tornei) {
        this.tornei = tornei;
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquadraModel that = (SquadraModel) o;
        return Objects.equals(idSquadra, that.idSquadra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSquadra);
    }


    @Override
    public String toString() {
        return "";
    }
}
