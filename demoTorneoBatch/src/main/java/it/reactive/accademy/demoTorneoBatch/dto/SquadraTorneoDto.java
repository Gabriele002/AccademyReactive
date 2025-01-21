package it.reactive.accademy.demoTorneoBatch.dto;

public class SquadraTorneoDto extends TipoRecord {

    String nomeTorneo;

    String nomeSquadra;

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }
}
