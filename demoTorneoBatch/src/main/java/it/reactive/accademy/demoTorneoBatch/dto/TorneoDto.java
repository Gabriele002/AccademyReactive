package it.reactive.accademy.demoTorneoBatch.dto;

public class TorneoDto extends TipoRecord {
    String nome;

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
