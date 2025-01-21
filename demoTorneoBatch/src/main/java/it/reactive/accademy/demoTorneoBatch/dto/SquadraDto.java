package it.reactive.accademy.demoTorneoBatch.dto;

public class SquadraDto extends TipoRecord {

    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    String nome;

    String colori_sociali;

    String tifoseriaNome;

    public String getTifoseriaNome() {
        return tifoseriaNome;
    }

    public void setTifoseriaNome(String tifoseriaNome) {
        this.tifoseriaNome = tifoseriaNome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColori_sociali() {
        return colori_sociali;
    }

    public void setColori_sociali(String colori_sociali) {
        this.colori_sociali = colori_sociali;
    }
}
