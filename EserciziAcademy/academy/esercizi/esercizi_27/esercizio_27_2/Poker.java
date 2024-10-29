package academy.esercizi.esercizi_27.esercizio_27_2;

import java.util.Arrays;
import java.util.Scanner;

public class Poker {

    private Mazzo mazzo;
    private Carta[] manoGiocatore;

    public Poker() {
        mazzo = new Mazzo();
        manoGiocatore = new Carta[5];
    }

    public void iniziaGioco() {
        manoGiocatore = mazzo.distribuisci(manoGiocatore.length);
        System.out.println("La tua mano: " + Arrays.toString(manoGiocatore));
        Scanner scn = new Scanner(System.in);

        Carta[] nuoveCarte = new Carta[manoGiocatore.length];
        int conteggioCarteNuove = 0;

        for (int i = 0; i < manoGiocatore.length; i++) {
            System.out.println("Vuoi tenere questa carta (Si o No)? " + manoGiocatore[i]);
            String scelta = scn.nextLine();
            if (scelta.equalsIgnoreCase("si")) {
                nuoveCarte[i] = manoGiocatore[i];
            } else {
                conteggioCarteNuove++;
            }
        }

        Carta[] carteScartate = mazzo.distribuisci(conteggioCarteNuove);
        int indiceNuoveCarte = 0;

        for (int i = 0; i < manoGiocatore.length; i++) {
            if (nuoveCarte[i] == null) {
                nuoveCarte[i] = carteScartate[indiceNuoveCarte++];
            }
        }

        manoGiocatore = nuoveCarte; // Aggiorna la mano del giocatore
        System.out.println("La tua nuova mano: " + Arrays.toString(manoGiocatore));
    }

}
