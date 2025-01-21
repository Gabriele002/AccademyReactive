package com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource;

import com.intesasanpaolo.bear.core.resource.BaseResource;
import io.swagger.models.auth.In;

public class CountryResource extends BaseResource {

    private Integer chiave;
    private String nome;
    private String lingua;
    private String continente;

    public CountryResource(Integer chiave, String nome, String lenguage, String continent) {
        this.chiave = chiave;
        this.nome = nome;
        this.lingua = lenguage;
        this.continente = continent;
    }

    public CountryResource() {
    }

    public long getChiave() {
        return chiave;
    }

    public void setChiave(Integer chiave) {
        this.chiave = chiave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }


    @Override
    public String toString() {
        return "CountryResource{" +
                "chiave=" + chiave +
                ", nome='" + nome + '\'' +
                ", lingua='" + lingua + '\'' +
                ", continente='" + continente + '\'' +
                '}';
    }
}
