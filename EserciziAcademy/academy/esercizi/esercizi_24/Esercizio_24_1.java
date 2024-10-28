package academy.esercizi.esercizi_24;

import java.util.Random;
import java.util.Scanner;

public class Esercizio_24_1 {
    public static void main(String[] args) {
        Esercizio_24_1 esercizio_24_1 = new Esercizio_24_1();
        esercizio_24_1.soluzione();
    }

    private static Scanner scn = new Scanner(System.in);
    private static Random random = new Random();

    public void soluzione() {
        gioca();
    }

    private static int mossaUtente(int biglieRimaste) {
        int mossa = 0;
        boolean validaMossa = false;
        while (!validaMossa) {
            System.out.printf("Biglie rimanenti: %d. Quante ne vuoi prendere? ", biglieRimaste);
            mossa = scn.nextInt();
            if (mossa > 0 && mossa <= biglieRimaste / 2) {
                validaMossa = true;
            } else {
                System.out.println("Mossa non valida! Devi prendere almeno 1 biglia e al massimo metà delle biglie rimanenti.");
            }
        }
        return mossa;
    }


    private static int mossaComputer(int biglieRimaste, int difficolta) {
        int mossa;
        if (difficolta == 0) {
            System.out.println("Modalita facile");
            mossa = random.nextInt(biglieRimaste / 2) + 1;
            System.out.printf("Il computer prende %d biglie.%n", mossa);
        } else {
            int[] potenze = {63, 31, 15, 7, 3};
            mossa = 0;
            System.out.println("Modalita difficile");
            for (int pot : potenze) {
                if (pot < biglieRimaste) {
                    mossa = biglieRimaste - (pot + 1);
                }
            }
            if (mossa <= 0 || mossa > biglieRimaste / 2) {
                mossa = random.nextInt(biglieRimaste / 2) + 1;
            }
            System.out.printf("Il computer prende %d biglie.%n", mossa);
        }
        return mossa;
    }

    private static void gioca() {
        int numeroBiglie = random.nextInt(91) + 10;
        int primoGiocatore = random.nextInt(2);
        int difficolta = random.nextInt(2);

        boolean giocoInCorso = true;
        while (giocoInCorso) {
            if (primoGiocatore == 0) {
                int mossa = mossaUtente(numeroBiglie);
                numeroBiglie -= mossa;
                if (numeroBiglie == 1) {
                    System.out.println("L'utente ha vinto");
                    giocoInCorso = false;
                }
                primoGiocatore = 1;
            } else {
                int mossa = mossaComputer(numeroBiglie, difficolta);
                numeroBiglie -= mossa;
                if (numeroBiglie == 1) {
                    System.out.println("Il computer ha vinto");
                    giocoInCorso = false;
                }
                primoGiocatore = 0;
            }
        }
    }
}

