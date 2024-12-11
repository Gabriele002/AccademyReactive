package it.reactive.torneoDemo.resource;

public class Trasferimenti {

    private Integer anno;
    private String nomeSquadraStorica;

    public Trasferimenti(Integer anno, String squadra) {
        this.anno = anno;
        this.nomeSquadraStorica = squadra;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public String getNomeSquadraStorica() {
        return nomeSquadraStorica;
    }

    public void setNomeSquadraStorica(String nomeSquadraStorica) {
        this.nomeSquadraStorica = nomeSquadraStorica;
    }
}
