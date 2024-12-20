package it.reactive.torneoDemo.model;

import it.reactive.torneoDemo.dto.resource.SquadraResponse;

import java.util.Set;

public class TorneoModel {

    private Integer idTorneo;
    private String nomeTorneo;
    private Set<SquadraResponse> squadre;

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public Set<SquadraResponse> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraResponse> squadre) {
        this.squadre = squadre;
    }
}
