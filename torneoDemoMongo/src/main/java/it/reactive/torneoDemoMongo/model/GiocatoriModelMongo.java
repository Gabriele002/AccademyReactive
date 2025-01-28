package it.reactive.torneoDemoMongo.model;

import it.reactive.torneoDemoMongo.dto.resource.Trasferimenti;

import java.util.List;
import java.util.Set;

public class GiocatoriModelMongo {

    private String nomeCognome;
    private Integer numeroAmmonizione = 0;
    private Set<Trasferimenti> trasferimenti;

    public Set<Trasferimenti> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(Set<Trasferimenti> trasferimenti) {
        this.trasferimenti = trasferimenti;
    }

    public Integer getNumeroAmmonizione() {
        return numeroAmmonizione;
    }

    public void setNumeroAmmonizione(Integer numeroAmmonizione) {
        this.numeroAmmonizione = numeroAmmonizione;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }
}
