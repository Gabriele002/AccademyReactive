package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "tifoseria")
public class TifoseriaModel {

    @Id
    @Column(name = "id")
    private Integer idTifoseria;
    private String nomeTifoseria;
    @ManyToOne
    @JoinColumn(name = "id_squadra")
    private SquadraModel squadra;

    public Integer getIdTifoseria() {
        return idTifoseria;
    }

    public void setIdTifoseria(Integer idTifoseria) {
        this.idTifoseria = idTifoseria;
    }

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public SquadraModel getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraModel squadra) {
        this.squadra = squadra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TifoseriaModel that = (TifoseriaModel) o;
        return Objects.equals(idTifoseria, that.idTifoseria);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idTifoseria);
    }
}
