package academy.esercizi.esercizi_26;

import java.util.Random;
import java.util.Scanner;

public class Esercizio_26_1 {
    public static void main(String[] args) {
        Esercizio_26_1 esercizio_26_1 = new Esercizio_26_1();
        esercizio_26_1.soluzione();
    }

    private static final int NUM_CARTE = 45;
    static Random random = new Random();
    Scanner scn = new Scanner(System.in);
    
    public void soluzione() {
        System.out.println("Inserisci il numero di mucchietti iniziali: ");
        int numMucchietti = scn.nextInt();
        gioca(numMucchietti);
    }

    private static int numeroCasuale(int tetto) {
        return random.nextInt(tetto) + 1;
    }

    private static int[] configurazioneIniziale(int numMucchietti) {
        int[] mucchietti = new int[numMucchietti];
        int somma = 0;

        for (int i = 0; i < mucchietti.length - 1; i++) {
            int tetto = NUM_CARTE - somma - (mucchietti.length - i - 1);
            mucchietti[i] = numeroCasuale(tetto);
            somma += mucchietti[i];
        }
        mucchietti[numMucchietti - 1] = NUM_CARTE - somma;
        stampa(mucchietti);
        return mucchietti;
    }

    private static void stampa(int[] mucchietti) {
        System.out.println("Mucchietti attuali:");
        int somma = 0;

        for (int mucchietto : mucchietti) {
            if (mucchietto > 0) {
                System.out.print(mucchietto + " ");
                somma += mucchietto;
            }
        }
        System.out.println();
        System.out.printf("Somma totale delle carte: %d%n", somma);
        if (somma != NUM_CARTE) {
            System.out.println("Errore: la somma delle carte non è 45!");
        }
    }

    private static int[] muovi(int[] mucchietti) {
        int sommaRimosse = 0;

        for (int i = 0; i < mucchietti.length; i++) {
            if (mucchietti[i] > 0) {
                sommaRimosse++;
                mucchietti[i]--;
            }
        }

        if (sommaRimosse > 0) {
            System.out.printf("Sono state rimosse %d carte, creando un nuovo mucchietto con %d carte.%n", sommaRimosse, sommaRimosse);

            boolean posizioneDisponibile = false;

            for (int i = 0; i < mucchietti.length; i++) {
                if (mucchietti[i] == 0) {
                    mucchietti[i] = sommaRimosse;
                    posizioneDisponibile = true;
                    break;
                }
            }

            if (!posizioneDisponibile) {
                int[] nuovoMucchietti = new int[mucchietti.length + 1];

                for (int i = 0; i < mucchietti.length; i++) {
                    nuovoMucchietti[i] = mucchietti[i];
                }
                nuovoMucchietti[nuovoMucchietti.length - 1] = sommaRimosse;
                mucchietti = nuovoMucchietti;
                System.out.println("Aggiunto un nuovo mucchietto alla fine.");
            }
        }
        stampa(mucchietti);
        return mucchietti;
    }


    private static void gioca(int numMucchietti) {
        int[] mucchietti = configurazioneIniziale(numMucchietti);
        int contandoMosse = 0;

        while (!finito(mucchietti)) {
            mucchietti = muovi(mucchietti);
            contandoMosse++;
        }

        System.out.printf("Il gioco è terminato in %d mosse.%n", contandoMosse);
    }




    private static boolean finito(int[] mucchietti) {
        int[] dimensioniPresente = new int[9];

        for (int mucchietto : mucchietti) {
            if (mucchietto >= 1 && mucchietto <= 9) {
                dimensioniPresente[mucchietto - 1] = 1;
            }
        }

        for (int j : dimensioniPresente) {
            if (j == 0) {
                return false;
            }
        }
        return true;
    }
}
