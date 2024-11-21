package academy.esercizi.biblioteca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Bibloteca {

    private List<Utente> utenti;
    private List<Libro> libri;

    final String pathBiblotecario = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\biblioteca\\file\\biblotecario";
    final String pathStudenti = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\biblioteca\\file\\studentiFile";
    final String pathLibri = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\biblioteca\\file\\libroFile";

    public Bibloteca() {
        this.utenti = new ArrayList<>();
        this.libri = new ArrayList<>();
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }

    public void creaUtente(String nome, String cognome, LivelliDiAccesso livelliDiAccesso, boolean isInizializazione) throws IOException {
        switch (livelliDiAccesso) {
            case Studente: {
                utenti.add(new Studente(nome, cognome));
                List<String> studenti = new ArrayList<>();
                for (Utente utente : utenti) {
                    if (utente instanceof Studente) {
                        String formattaStringa = utente.getNome() + ";" + utente.getCognome();
                        studenti.add(formattaStringa);
                    }
                }
                scriviFile(pathStudenti, studenti);
                break;
            }
//                if (!isInizializazione) {
//                    scriviSuFile(pathStudenti, nome, cognome);
//                }
            case User:
                utenti.add(new Utente(nome, cognome));
                break;
            case Bibliotecario:
                Bibliotecario bibliotecario = new Bibliotecario(nome, cognome);
                if (!isInizializazione) {
                    scriviSuFile(pathBiblotecario, nome, cognome);
                }
                utenti.add(bibliotecario);
                break;
        }
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

    public void rimuoviLibro(Libro libroDaRimuovere) throws IOException {
        libri.remove(libroDaRimuovere);
        eliminaDaFile(pathLibri);
    }

    public void creaLibro(String autore, String titolo) throws IOException {
        libri.add(new Libro(autore, titolo));
    }

//    public <T> void scriviSuFile(String path, String nome, String cognome, List<T> lista) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
//        String formattaStringa = nome + ";" + cognome;
//        Iterator<T> iterator = lista.iterator();
//        while (iterator.hasNext()) {
//            T utente = iterator.next();
//            if (utente instanceof Bibliotecario){
//
//            } else if(utente instanceof  Studente){
//
//            }
//        }
//        bufferedWriter.close();
//    }


    public void scriviSuFile(String path, String nome, String cognome) throws IOException {
        List<String> riga = Files.readAllLines(Paths.get(path));
        String nuovoUtente = nome + ";" + cognome;

        boolean duplicato = riga.stream().anyMatch(utente -> utente.equalsIgnoreCase(nuovoUtente));
        if (!duplicato) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
            bufferedWriter.write("\n" + nuovoUtente);
            bufferedWriter.close();
        }
    }

    public void scriviFile(String path, List<String> lista){
        try {
            Files.write(Paths.get(path), lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void eliminaDaFile(String path) throws IOException {
        List<String> listaDiLibri = new ArrayList<>();
        for (Libro libro : libri) {
            String formattata = libro.getTitolo() + ";" + libro.getAutore();
            listaDiLibri.add(formattata);
        }
        Files.write(Paths.get(path), listaDiLibri);
    }
}
