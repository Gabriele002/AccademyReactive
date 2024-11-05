package academy.esercizi.esercizi_24.esercizio_24_1;

import java.util.Random;
import java.util.Scanner;

public class Gioco {
    private static final Scanner scn = new Scanner(System.in);
    private static final Random random = new Random();

    public void inizia() {
        int numeroBiglie = random.nextInt(91) + 10;
        Turno turnoCorrente = random.nextBoolean() ? Turno.UTENTE : Turno.COMPUTER;
        Difficolta difficolta = random.nextBoolean() ? Difficolta.FACILE : Difficolta.DIFFICILE;

        do {
            if (turnoCorrente == Turno.UTENTE) {
                int mossa = mossaUtente(numeroBiglie);
                numeroBiglie -= mossa;
                if (numeroBiglie == 1) {
                    System.out.println("L'utente ha vinto");
                    return;
                }
                turnoCorrente = Turno.COMPUTER;
            } else {
                int mossa = mossaComputer(numeroBiglie, difficolta);
                numeroBiglie -= mossa;
                if (numeroBiglie == 1) {
                    System.out.println("Il computer ha vinto");
                    return;
                }
                turnoCorrente = Turno.UTENTE;
            }
        } while (numeroBiglie > 1);
    }

    private static int mossaUtente(int biglieRimaste) {
        int mossa;
        do {
            System.out.printf("Biglie rimanenti: %d. Quante ne vuoi prendere? ", biglieRimaste);
            mossa = scn.nextInt();
            if (mossa <= 0 || mossa > biglieRimaste / 2) {
                System.out.printf("Mossa non valida! Devi prendere almeno 1 biglia e al massimo %d", biglieRimaste /2);
            }
        } while (mossa <= 0 || mossa > biglieRimaste / 2);
        return mossa;
    }

    private static int mossaComputer(int biglieRimaste, Difficolta difficolta) {
        int mossa;
        if (difficolta == Difficolta.FACILE) {
            System.out.println("Modalità facile");
            mossa = random.nextInt(biglieRimaste / 2) + 1;
        } else {
            System.out.println("Modalità difficile");
            int potenza = 1;
            while (potenza * 2 <= biglieRimaste) {
                potenza *= 2;
            }
            mossa = (biglieRimaste == potenza) ? 1 : biglieRimaste - potenza;
            if (mossa <= 0 || mossa > biglieRimaste / 2) {
                mossa = random.nextInt(biglieRimaste / 2) + 1;
            }
        }
        System.out.printf("Il computer prende %d biglie.%n", mossa);
        return mossa;
    }
}
