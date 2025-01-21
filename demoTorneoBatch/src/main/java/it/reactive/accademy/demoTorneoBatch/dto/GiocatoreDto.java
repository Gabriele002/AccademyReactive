package it.reactive.accademy.demoTorneoBatch.dto;

public class GiocatoreDto extends TipoRecord {

    private String nomeCognome;

    public String getNomeCognome() {
        return nomeCognome;
    }

    public String nomeSquadra;

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }
}
