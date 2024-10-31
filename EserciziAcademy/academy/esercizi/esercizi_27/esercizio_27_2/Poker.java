package academy.esercizi.esercizi_27.esercizio_27_2;

import java.util.Arrays;
import java.util.Scanner;

public class Poker {

    private final Mazzo mazzo;
    private Carta[] manoGiocatore = new Carta[5];
    private int punteggioFinale = 0;
    private boolean isFull = false;
    private boolean isDoppiaCoppia = false;
    private boolean isCoppia = false;
    boolean isColore = false;
    boolean isScala = false;
    boolean isScalaReale = false;

    public boolean isFull() {
        return isFull;
    }

    public int getPunteggioFinale() {
        return punteggioFinale;
    }

    public void setPunteggioFinale(int punteggioFinale) {
        this.punteggioFinale = punteggioFinale;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public boolean isDoppiaCoppia() {
        return isDoppiaCoppia;
    }

    public void setDoppiaCoppia(boolean doppiaCoppia) {
        isDoppiaCoppia = doppiaCoppia;
    }

    public boolean isCoppia() {
        return isCoppia;
    }

    public void setCoppia(boolean coppia) {
        isCoppia = coppia;
    }

    public boolean isPoker() {
        return isPoker;
    }

    public void setPoker(boolean poker) {
        isPoker = poker;
    }

    public boolean isTris() {
        return isTris;
    }

    public void setTris(boolean tris) {
        isTris = tris;
    }

    private boolean isPoker = false;
    private boolean isTris = false;

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
        for (Carta carta : carteNuove) {
            for (int i = 0; i < manoNuova.length; i++) {
                if (manoNuova[i] == null) {
                    manoNuova[i] = carta;
                    break;
                }
            }
        }

        manoGiocatore = manoNuova;
        System.out.println("La tua nuova mano: " + Arrays.toString(manoGiocatore));
    }


    public void valutaCoppiaDoppiaFullPoker() {
        int numeriDiCarteUguali = 1;
        int valoreCarta = 0;

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
            numeriDiCarteUguali = numeriDiCarteUgualiForfettario;
            if (valoreCarta != cartaIniziale.getValore() && numeriDiCarteUgualiForfettario > 1) {
                if (numeriDiCarteUguali == 2) {
                    if (isCoppia) {
                        isDoppiaCoppia = true;
                        isCoppia = false;
                    } else {
                        isCoppia = true;
                    }

                    punteggioFinale += punteggio;

                } else if (numeriDiCarteUguali == 3) {
                    isTris = true;
                    punteggioFinale += punteggio;
                } else if (numeriDiCarteUguali == 4) {
                    isPoker = true;
                    punteggioFinale += punteggio;
                }
                valoreCarta = punteggio / numeriDiCarteUguali;
            }
        }
        if (isTris && isCoppia) {
            isTris = false;
            isCoppia = false;
            isFull = true;
        }
    }

    public void valutaScalaColore() {
        int[] valori = new int[manoGiocatore.length];
        String semeIniziale = manoGiocatore[0].getSeme();

        for (int i = 0; i < manoGiocatore.length; i++) {
            valori[i] = manoGiocatore[i].getValore();
        }
        Arrays.sort(valori);

        boolean hasScala = true;
        boolean hasColore = true;

        for (int i = 1; i < manoGiocatore.length; i++) {
            if (!manoGiocatore[i].getSeme().equals(semeIniziale)) {
                hasColore = false;
                break;
            }
        }

        for (int i = 1; i < manoGiocatore.length; i++) {
            if (valori[i] != valori[i - 1] + 1) {
                hasScala = false;
                break;
            }
        }

        if (hasColore) {
            isColore = true;
            punteggioFinale += 50;
        }

        if (hasScala) {
            isScala = true;
            punteggioFinale += 40;
        }

        if (isColore && isScala) {
            isScalaReale = true;
            punteggioFinale += 100;
        }
    }

    public void valutaMano() {
        punteggioFinale = 0;

        valutaCoppiaDoppiaFullPoker();
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


}
