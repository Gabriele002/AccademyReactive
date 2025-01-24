package it.reactive.torneoDemoMongo.dto.resource;

import it.reactive.torneoDemoMongo.model.SquadraModelMongo;

import java.util.Set;

public class TorneoResponse {
    private String idTorneo;
    private String nomeTorneo;
    private Set<SquadraResponse> squadre;


    public String getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(String idTorneo) {
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
