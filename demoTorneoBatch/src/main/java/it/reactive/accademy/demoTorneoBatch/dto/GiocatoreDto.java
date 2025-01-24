package it.reactive.accademy.demoTorneoBatch.dto;

public class GiocatoreDto extends TipoRecord {

    private String nomeCognome;

    private Integer idSquadra;

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

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
