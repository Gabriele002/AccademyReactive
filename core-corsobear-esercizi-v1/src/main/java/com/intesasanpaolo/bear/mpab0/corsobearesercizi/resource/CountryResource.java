package com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource;

import com.intesasanpaolo.bear.core.resource.BaseResource;

public class CountryResource extends BaseResource {

    private long chiave;
    private String nome;
    private String lenguage;
    private String continent;

    public CountryResource(long chiave, String nome, String lenguage, String continent) {
        this.chiave = chiave;
        this.nome = nome;
        this.lenguage = lenguage;
        this.continent = continent;
    }

    public CountryResource() {
    }

    public long getChiave() {
        return chiave;
    }

    public void setChiave(long chiave) {
        this.chiave = chiave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLenguage() {
        return lenguage;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
