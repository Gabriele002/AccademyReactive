package academy.esercizi.biblioteca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Bibloteca {

    private List<Utente> utenti;
    private List<Libro> libri;

    public Bibloteca() {
        this.utenti = new ArrayList<>();
        this.libri = new ArrayList<>();
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }


    public void creaUtente(String nome, String cognome, LivelliDiAccesso livelliDiAccesso) {
        switch (livelliDiAccesso) {
            case Studente:
                utenti.add(new Studente(nome, cognome));
                break;
            case User:
                utenti.add(new Utente(nome, cognome));
                break;
            case Bibliotecario:
                utenti.add(new Bibliotecario(nome, cognome));
                break;
        }

    }

    public void creaLibro(String autore, String titolo) throws IOException {
        String path = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\biblioteca\\file\\libroFile";
        libri.add(new Libro(autore, titolo));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
        String formattaStringa =autore+ ";"  + titolo;
        for (Libro libro : libri) {
            if ((libro.getTitolo()).equalsIgnoreCase(titolo) || (libro.getAutore().equalsIgnoreCase(autore))){
                bufferedWriter.write("\n" +formattaStringa);
            }
        }
        bufferedWriter.close();
    }



    public Libro prendiInprestitoLibro(Libro libroDaPrendere) {
        for (int i = 0; i < libri.size(); i++) {
            if (Objects.equals(libri.get(i).getTitolo(), libroDaPrendere.getTitolo())) {
                if (libri.get(i).verficaDisponibilita()) {
                    libri.set(i, libroDaPrendere.prestare());
                    return libri.get(i);
                }
            }
        }
        return null;
    }

    public Optional<Utente> trovaUtenteEsistente(String nome) {
        for (Utente utente : utenti) {
            if (utente.getNome().equalsIgnoreCase(nome)) {
                return Optional.of(utente);
            }
        }
        return Optional.empty();
    }

    public Optional<Libro> trovaLibroPerTitolo(String titolo) {
        for (Libro libro : libri) {
            if (libro.getTitolo().trim().equalsIgnoreCase(titolo)) {
                return Optional.of(libro);
            }
        }
        return Optional.empty();
    }

    public void rimuoviLibro(Libro libroDaRimuovere) {
        libri.remove(libroDaRimuovere);
    }
}
