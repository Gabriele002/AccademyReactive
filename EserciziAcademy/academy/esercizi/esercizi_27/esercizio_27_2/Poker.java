package academy.esercizi.esercizi_27.esercizio_27_2;

import java.util.Arrays;
import java.util.Scanner;

public class Poker {

    private Mazzo mazzo;
    private Carta[] manoGiocatore = new Carta[5];

    public Poker() {
        mazzo = new Mazzo();
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


//    public void valutaCoppia() {
//        int punteggio = 0;
//        boolean giroUno = true;
//        for (Carta carta : manoGiocatore) {
//            if (giroUno) {
//                punteggio = carta.getValore();
//                giroUno = false;
//            } else {
//                if (punteggio == carta.getValore()) {
//                    punteggio = +carta.getValore();
//                }
//            }
//        }
//        int punteggio2 = 0;
//        boolean giroDue = true;
//        for (Carta carta : manoGiocatore) {
//            if (giroDue) {
//                punteggio = carta.getValore();
//                giroDue = false;
//            } else {
//                if (punteggio == carta.getValore()) {
//                    punteggio = +carta.getValore();
//                }
//            }
//        }
//    }


    public void valutaDoppia() {
        manoGiocatore = new Carta[5];
        manoGiocatore[0] = new Carta("fiori",5);
        manoGiocatore[1] = new Carta("fiori",5);
        manoGiocatore[2] = new Carta("fiori",5);
        manoGiocatore[3] = new Carta("fiori",6);
        manoGiocatore[4] = new Carta("fiori",11);
        int punteggioFinale = 0;
        int numeriDiCarteUguali = 1;
        boolean isCoppia = false;
        boolean isTris = false;
        boolean poker = false;

        for (int j = 0; j < manoGiocatore.length; j++) {
            Carta cartaIniziale = manoGiocatore[j];
            int punteggio = cartaIniziale.getValore();
            int numeriDiCarteUgualiForfettario = 1;
            for (int i = 0; i < manoGiocatore.length; i++) {
                if (i > j) {
                    if (cartaIniziale.getValore() == manoGiocatore[i].getValore()) {
                        punteggio += manoGiocatore[i].getValore();
                        numeriDiCarteUgualiForfettario++;
                    }
                }
            }
            if (punteggio > punteggioFinale){
                punteggioFinale = punteggio;
                numeriDiCarteUguali = numeriDiCarteUgualiForfettario;
            }
        }
    }


}
