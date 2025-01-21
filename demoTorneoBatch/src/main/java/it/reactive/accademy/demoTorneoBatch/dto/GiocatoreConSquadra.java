package it.reactive.accademy.demoTorneoBatch.dto;

public class GiocatoreConSquadra {

    Integer idGiocatore;
    String nomeCognome;
    Integer numeroAmmonizioni;
    String nomeSquadra;
    String coloriSociali;
    String nomeTifoseria;


    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public Integer getIdGiocatore() {
        return idGiocatore;
    }

    public void setIdGiocatore(Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public Integer getNumeroAmmonizioni() {
        return numeroAmmonizioni;
    }

    public void setNumeroAmmonizioni(Integer numeroAmmonizioni) {
        this.numeroAmmonizioni = numeroAmmonizioni;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    @Override
    public String toString() {
        return "GiocatoreConSquadra{" +
                "idGiocatore=" + idGiocatore +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", numeroAmmonizioni=" + numeroAmmonizioni +
                ", nomeSquadra='" + nomeSquadra + '\'' +
                ", coloriSociali='" + coloriSociali + '\'' +
                ", nomeTifoseria='" + nomeTifoseria + '\'' +
                '}';
    }

}
