package com.intesasanpaolo.bear.mpab0.corsobearesercizi.model;

public class CountryModel {
    Integer id;
    String info;

    public CountryModel(Integer id, String info) {
        this.id = id;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }
}
