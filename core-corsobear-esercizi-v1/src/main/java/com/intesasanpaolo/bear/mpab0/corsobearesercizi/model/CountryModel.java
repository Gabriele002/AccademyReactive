package com.intesasanpaolo.bear.mpab0.corsobearesercizi.model;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class CountryModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "info")
    String info;

    public CountryModel(Integer id, String info) {
        this.id = id;
        this.info = info;
    }

    public CountryModel() {
    }

    public Integer getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
