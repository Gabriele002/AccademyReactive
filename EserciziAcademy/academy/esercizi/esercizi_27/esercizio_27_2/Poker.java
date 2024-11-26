package academy.esercizi.esercizi_27.esercizio_27_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Poker {

    private final int manoIniziale = 5;

    private final Mazzo mazzo;
    private Carta[] manoGiocatore = new Carta[manoIniziale];
    private int punteggioFinale = 0;
    private boolean isFull = false;
    private boolean isDoppiaCoppia = false;
    private boolean isCoppia = false;
    boolean isColore = false;
    boolean isScala = false;
    boolean isScalaReale = false;
    private boolean isPoker = false;
    private boolean isTris = false;

    public Poker() {
        mazzo = new Mazzo();
    }

    public void iniziaGioco() {
        manoGiocatore = mazzo.distribuisci(manoIniziale);

        ArrayList<String> manoInizialeFormattata = formattaMano(manoGiocatore);
        System.out.print("La tua mano: ");
        manoInizialeFormattata.forEach(string -> System.out.print(string + ", "));
        System.out.println();

        Scanner scn = new Scanner(System.in);
        Carta[] manoNuova = new Carta[manoIniziale];
        int conteggioCarteNuove = 0;
        for (int i = 0; i < manoInizialeFormattata.size(); i++) {
            System.out.println("Vuoi tenere questa carta (Si o No)? " + manoInizialeFormattata.get(i));
            String scelta = scn.nextLine();
            if (scelta.equalsIgnoreCase("si")) {
                manoNuova[i] = manoGiocatore[i];
            } else {
                conteggioCarteNuove++;
            }
        }

        Carta[] carteNuove = mazzo.distribuisci(conteggioCarteNuove);
        for (Carta carta : carteNuove) {
            for (int i = 0; i < manoNuova.length; i++) {
                if (manoNuova[i] == null) {
                    manoNuova[i] = carta;
                    break;
                }
            }
        }

        manoGiocatore = manoNuova;
        ArrayList<String> manoNuovaformattata = formattaMano(manoGiocatore);
        System.out.print("La tua nuova mano: ");
        manoNuovaformattata.forEach(string -> System.out.print(string + ", "));
        System.out.println();
    }

    private void valutaCoppiaFullTris() {
        int[] frequenze = new int[15];
        for (Carta carta : manoGiocatore) {
            frequenze[carta.getValore()]++;
        }
        for (int i = 1; i < frequenze.length; i++) {
            if (frequenze[i] == 4) {
                isPoker = true;
                punteggioFinale += i * 4;
            } else if (frequenze[i] == 3) {
                isTris = true;
                punteggioFinale += i * 3;
            } else if (frequenze[i] == 2) {
                if (isCoppia) {
                    isDoppiaCoppia = true;
                } else {
                    isCoppia = true;
                }
                punteggioFinale += i * 2;
            }
        }

        if (isTris && isCoppia) {
            isFull = true;
            isTris = false;
            isCoppia = false;
        }
    }

    public void valutaScalaColore() {
        int[] valori = new int[manoGiocatore.length];
        String semeIniziale = manoGiocatore[0].getSeme();
        boolean colore = true;

        for (int i = 0; i < manoGiocatore.length; i++) {
            valori[i] = manoGiocatore[i].getValore();
            if (!manoGiocatore[i].getSeme().equals(semeIniziale)) {
                colore = false;
            }
        }

        Arrays.sort(valori);
        isScala = true;
        for (int i = 1; i < valori.length; i++) {
            if (valori[i] != valori[i - 1] + 1) {
                isScala = false;
                break;
            }
        }

        isColore = colore;

        if (isColore) {
            punteggioFinale += 50;
        }
        if (isScala) {
            punteggioFinale += 40;
        }
        if (isColore && isScala) {
            isScalaReale = true;
            punteggioFinale += 100;
        }
    }


    public void valutaMano() {
        punteggioFinale = 0;
        valutaCoppiaFullTris();
        valutaScalaColore();

        String combinazione = "Nessuna combinazione";
        if (isScalaReale) {
            combinazione = "Scala Reale";
        } else if (isPoker) {
            combinazione = "Poker";
        } else if (isFull) {
            combinazione = "Full";
        } else if (isColore) {
            combinazione = "Colore";
        } else if (isScala) {
            combinazione = "Scala";
        } else if (isTris) {
            combinazione = "Tris";
        } else if (isDoppiaCoppia) {
            combinazione = "Doppia Coppia";
        } else if (isCoppia) {
            combinazione = "Coppia";
        }
        System.out.println("Combinazione: " + combinazione + ", Punteggio Finale: " + punteggioFinale);
    }

    enum Figura {
        Asso,
        Jack,
        Donna,
        Re
    }

    public ArrayList<String> formattaMano(Carta[] manoGiocatore) {
        ArrayList<String> stampaCarte = new ArrayList<>();
        for (Carta carta : manoGiocatore) {
            String descrizioneCarta;
            switch (carta.getValore()) {
                case 11:
                    descrizioneCarta = Figura.Jack + " di " + carta.getSeme();
                    break;
                case 12:
                    descrizioneCarta = Figura.Donna + " di " + carta.getSeme();
                    break;
                case 13:
                    descrizioneCarta = Figura.Re + " di " + carta.getSeme();
                    break;
                case 14:
                    descrizioneCarta = Figura.Asso + " di " + carta.getSeme();
                    break;
                default:
                    descrizioneCarta = carta.getValore() + " di " + carta.getSeme();
            }
            stampaCarte.add(descrizioneCarta);
        }
        return stampaCarte;
    }
}
