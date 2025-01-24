package it.reactive.accademy.demoTorneoBatch.dto;

public class TorneoDto extends TipoRecord {
    String nome;
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TorneoDto " +
                "nome='" + nome + '\'' ;
    }
}
