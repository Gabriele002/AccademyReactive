package academy.esercizi.biblioteca;

import java.util.Objects;

public class Utente {


    private String nome;
    private String cognome;
    private int id;
    private static int counter = 0;


    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Utente " +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", id=" + id ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return id == utente.id && Objects.equals(nome, utente.nome) && Objects.equals(cognome, utente.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, id);
    }
}
