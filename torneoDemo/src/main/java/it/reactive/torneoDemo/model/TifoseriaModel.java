package it.reactive.torneoDemo.model;

import javax.persistence.*;

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

}
