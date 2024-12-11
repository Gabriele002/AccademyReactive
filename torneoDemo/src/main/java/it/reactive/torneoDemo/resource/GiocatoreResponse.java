package it.reactive.torneoDemo.resource;

import java.util.HashSet;
import java.util.Set;

public class GiocatoreResponse {
    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    private String squadra;
    private Set<Trasferimenti> trasferimenti = new HashSet<>();



    public GiocatoreResponse(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni, Set<Trasferimenti> trasferimenti, String squadra) {
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.trasferimenti= trasferimenti;
        this.squadra = squadra;
    }

    public GiocatoreResponse() {}

    public Integer getIdGiocatore() {
        return idGiocatore;
    }

    public String getSquadra() {
        return squadra;
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
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

    public Set<Trasferimenti> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(Set<Trasferimenti> trasferimenti) {
        this.trasferimenti = trasferimenti;
    }
}

