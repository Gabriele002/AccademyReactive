package it.reactive.torneoDemoMongo.dto.resource;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GiocatoreResponse {
    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    private Set<Trasferimenti> trasferimenti = new HashSet<>();

    public GiocatoreResponse(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni, Set<Trasferimenti> trasferimenti ) {
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.trasferimenti= trasferimenti;
    }

    public GiocatoreResponse() {}

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

    public Set<Trasferimenti> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(Set<Trasferimenti> trasferimenti) {
        this.trasferimenti = trasferimenti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiocatoreResponse that = (GiocatoreResponse) o;
        return Objects.equals(idGiocatore, that.idGiocatore) && Objects.equals(nomeCognome, that.nomeCognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGiocatore, nomeCognome);
    }
}

