package it.reactive.torneoDemo.model;

public class GiocatoriModel {
    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    private SquadraModel squadra;

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

    public SquadraModel getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraModel squadra) {
        this.squadra = squadra;
    }

    @Override
    public String toString() {
        return "GiocatoriModel{" +
                "idGiocatore=" + idGiocatore +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", numeroAmmonizioni=" + numeroAmmonizioni +
                ", squadra=" + squadra +
                '}';
    }
}
