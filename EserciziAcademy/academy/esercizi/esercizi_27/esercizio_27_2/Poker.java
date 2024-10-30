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
        Carta[] manoNuova = new Carta[manoGiocatore.length];
        int conteggioCarteNuove = 0;

        for (int i = 0; i < manoGiocatore.length; i++) {
            System.out.println("Vuoi tenere questa carta (Si o No)? " + manoGiocatore[i]);
            String scelta = scn.nextLine();
            if (scelta.equalsIgnoreCase("si")) {
                manoNuova[i] = manoGiocatore[i];
            } else {
                conteggioCarteNuove++;
            }
        }

        Carta[] carteNuove = mazzo.distribuisci(conteggioCarteNuove);
        for (int j = 0; j < carteNuove.length; j++) {
            for (int i = 0; i < manoNuova.length; i++) {
                if (manoNuova[i] == null) {
                    manoNuova[i] = carteNuove[j];
                    break;
                }
            }
        }

        manoGiocatore = manoNuova;
        System.out.println("La tua nuova mano: " + Arrays.toString(manoGiocatore));
    }


    public void valutaPunteggio() {

    }
}
