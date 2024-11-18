package academy.esercizi.biblioteca;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static academy.esercizi.biblioteca.Bibliotecario.aggiungiLibro;
import static academy.esercizi.biblioteca.Bibliotecario.rimuoviLibro;

public class Main {

    private static final int ENTRA_BIBLIOTECARIO = 1;
    private static final int ENTRA_STUDENTE = 2;
    private static final int CREA_UTENTE = 3;
    private static final int GUARDA_LIBRI = 4;

    public static void main(String[] args) throws IOException {
        InizializaBibloteca inizializzaBibloteca = new InizializaBibloteca();
        Bibloteca bibloteca = inizializzaBibloteca.bibloteca();
        Scanner scn = new Scanner(System.in);
        int scelta;
        do {
            mostraMenu();
            scelta = Integer.parseInt(scn.nextLine());

            switch (scelta) {
                case ENTRA_BIBLIOTECARIO:
                    entraComeBibliotecario(bibloteca, scn);
                    break;
                case ENTRA_STUDENTE:
                    entraComeStudente(bibloteca, scn);
                    break;
                case CREA_UTENTE:
                    creaNuovoUtente(bibloteca, scn);
                    break;
                case GUARDA_LIBRI:
                    mostraLibriDisponibili(bibloteca);
                    break;
                default:
                    System.out.println("Sei uscito con sucesso");
            }
        } while (scelta != 0);
    }

    private static void mostraMenu() {
        System.out.println("1) Entra come bibliotecario");
        System.out.println("2) Entra come studente");
        System.out.println("3) Crea un nuovo utente");
        System.out.println("4) Guarda libri");
        System.out.println("0) Per uscire");

    }

    private static void entraComeBibliotecario(Bibloteca bibloteca, Scanner scn) throws IOException {
        System.out.println("Inserisci il nome del bibliotecario");
        String nome = scn.nextLine();
        Optional<Utente> utente = bibloteca.trovaUtenteEsistente(nome);

        if (utente.isPresent()) {
            if (utente.get() instanceof Bibliotecario) {
                System.out.println("Utente: " + utente.get().getNome() + " loggato con successo");
                gestisciLibri(bibloteca, scn);
            } else {
                System.out.println("Non sei un bibliotecario.");
            }
        } else {
            System.out.println("Utente non trovato.");
        }
    }

    private static void entraComeStudente(Bibloteca bibloteca, Scanner scn) {
        System.out.println("Inserisci il nome dello studente");
        String nome = scn.nextLine();
        Optional<Utente> utente = bibloteca.trovaUtenteEsistente(nome);

        if (utente.isPresent()) {
            if (utente.get() instanceof Studente) {
                System.out.println("Utente: " + utente.get().getNome() + " loggato con successo");
                System.out.println("Lista dei libri in prestito");
                for (Libro libro : ((Studente) utente.get()).libriStudente) {
                    System.out.println(libro);
                }
            } else {
                System.out.println("Non sei uno studente.");
            }
        } else {
            System.out.println("Utente non trovato.");
        }
    }

    private static void creaNuovoUtente(Bibloteca bibloteca, Scanner scn) {
        String nome = "";
        String cognome = "";

        while (!Utility.controllaStringheNonVuote(nome, cognome)) {
            if (nome.isEmpty()) {
                System.out.println("Inserisci il nome:");
                nome = scn.next();
            }
            if (cognome.isEmpty()) {
                System.out.println("Inserisci il cognome:");
                cognome = scn.next();
            }
            if (nome.isEmpty() || cognome.isEmpty()) {
                System.out.println("Errore: entrambi i campi (nome e cognome) devono essere compilati.");
            }
        }
        bibloteca.creaUtente(nome, cognome, LivelliDiAccesso.User);
        System.out.println("Utente creato con successo.");
    }


    private static void mostraLibriDisponibili(Bibloteca bibloteca) {
        System.out.println("Lista dei libri disponibili:");
        for (Libro libro : bibloteca.getLibri()) {
            if (!(libro instanceof LibroPrestato)) {
                System.out.println(libro);
            }
        }
    }

    private static void gestisciLibri(Bibloteca bibloteca, Scanner scn) throws IOException {
        int sceltaLibri;
        do {
            System.out.println("\nGestione libri:");
            System.out.println("1) Aggiungi un libro");
            System.out.println("2) Rimuovi un libro");
            System.out.println("3) Torna al menu principale");
            sceltaLibri = Integer.parseInt(scn.nextLine());

            switch (sceltaLibri) {
                case 1:
                    aggiungiLibro(bibloteca, scn);
                    break;
                case 2:
                    rimuoviLibro(bibloteca, scn);
                    break;
                case 3:
                    System.out.println("Tornando al menu principale...");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        } while (sceltaLibri != 3);
    }
}
