package it.reactive.torneoDemoMongo.model;

import it.reactive.torneoDemoMongo.dto.resource.Trasferimenti;

import java.util.List;

public class GiocatoriModelMongo {

    private String nomeCognome;
    private Integer numeroAmmonizione = 0;
    private List<Trasferimenti> trasferimenti;

    public List<Trasferimenti> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(List<Trasferimenti> trasferimenti) {
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
