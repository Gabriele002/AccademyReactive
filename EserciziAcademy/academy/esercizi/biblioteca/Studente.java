package academy.esercizi.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Studente extends Utente{

    List<Libro> libriStudente;

    public Studente(String nome, String cognome) {
        super(nome, cognome);
        this.libriStudente = new ArrayList<>();
    }

    public List<Libro> getLibriStudente() {
        return libriStudente;
    }

    public void setLibriStudente(List<Libro> libriStudente) {
        this.libriStudente = libriStudente;
    }

    public void prendiInPrestitoLibroStudente(Libro libro, Bibloteca bibloteca){
        Optional<Libro> libroDaPredenre = Optional.ofNullable(bibloteca.prendiInprestitoLibro(libro));
        libroDaPredenre.ifPresent(value -> libriStudente.add(value));
    }

    public Libro restituisciLibro(LibroPrestato libro){
        if (libriStudente.contains(libro)){
            return libro;
        } else {
            System.out.println("Il libro che stai cercando di restituire non e in tuo possesso");
        }
        return libro;
    }

}
