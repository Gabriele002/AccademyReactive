package it.reactive.torneoDemo.dto.resource;

public class Trasferimenti {

    private Integer anno;
    private String squadra;

    public Trasferimenti(Integer anno, String squadra) {
        this.anno = anno;
        this.squadra = squadra;
    }

    public Trasferimenti() {
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public String getSquadra() {
        return squadra;
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }
}
