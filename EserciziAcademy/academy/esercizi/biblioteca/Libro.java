package academy.esercizi.biblioteca;

import java.time.LocalDate;
import java.util.Objects;

public class Libro implements Prestabile {
    private String autore;
    private String titolo;
    private int id;
    private static int counter = 0;

    public Libro(String titolo, String autore) {
        this.autore = autore;
        this.titolo = titolo;
        this.id = ++counter;
    }

    public Libro(String titolo, String autore, int id) {
        this.autore = autore;
        this.titolo = titolo;
        this.id = id;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public LibroPrestato prestare() {
        return new LibroPrestato(autore, titolo, id,LocalDate.now());
    }

    @Override
    public Libro restituire() {
        return new Libro(autore,titolo);
    }

    @Override
    public boolean verficaDisponibilita() {
        return !(this instanceof LibroPrestato);
    }

    @Override
    public String toString() {
        return "Libro " +
                "autore='" + autore + '\'' +
                ", titolo='" + titolo + '\'' +
                ", id=" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id == libro.id && Objects.equals(autore, libro.autore) && Objects.equals(titolo, libro.titolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autore, titolo, id);
    }
}
