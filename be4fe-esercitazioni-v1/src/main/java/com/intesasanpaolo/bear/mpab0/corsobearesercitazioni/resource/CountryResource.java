package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource;


import java.time.Instant;

public class CountryResource {
    private String key;
    private Instant oraAggiornamento;
    private String lingua;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Instant getOraAggiornamento() {
        return oraAggiornamento;
    }

    public void setOraAggiornamento(Instant oraAggiornamento) {
        this.oraAggiornamento = oraAggiornamento;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
}
