package academy.esercizi.biblioteca;


import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Bibliotecario extends Utente {

    public Bibliotecario(String nome, String cognome) {
        super(nome, cognome);
    }

    static void aggiungiLibro(Bibloteca bibloteca, Scanner scn) throws IOException {
        String titolo = "";
        String autore = "";
        do {
            if (titolo.isEmpty()) {
                System.out.println("Inserisci il titolo del libro da aggiungere:");
                titolo = scn.nextLine();
            }
            if (autore.isEmpty()) {
                System.out.println("Inserisci l'autore del libro:");
                autore = scn.nextLine();
            }
            if (titolo.isEmpty() || autore.isEmpty()) {
                System.out.println("Entrambi i campi (titolo e autore) devono essere compilati. Riprova.");
            }
        }while (Utility.controllaStringheNonVuote(titolo, autore));
        bibloteca.creaLibro(autore, titolo);
        System.out.println("Libro aggiunto con successo!");
    }

    static void rimuoviLibro(Bibloteca bibloteca, Scanner scn) throws IOException {
        System.out.println("Inserisci il titolo del libro da rimuovere:");
        String titolo = scn.nextLine();
        Optional<Libro> libroDaRimuovere = bibloteca.trovaLibroPerTitolo(titolo);
        if (libroDaRimuovere.isPresent()) {
            bibloteca.rimuoviLibro(libroDaRimuovere.get());
            System.out.println("Libro rimosso con successo!");
        } else {
            System.out.println("Libro non trovato.");
        }
    }


}
