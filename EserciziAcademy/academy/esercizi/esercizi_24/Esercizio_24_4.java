package academy.esercizi.esercizi_24;

import java.util.Scanner;

public class Esercizio_24_4 {
    public static void main(String[] args) {
        Esercizio_24_4 esercizio_24_4 = new Esercizio_24_4();
        esercizio_24_4.soluzione();
    }

    static final Scanner scn = new Scanner(System.in);

    public void soluzione() {

        String[] nomi = new String[3];
        int[] valori = new int[3];

        for (int i = 0; i < nomi.length; i++) {
            System.out.print("Inserisci un nome: ");
            nomi[i] = scn.nextLine();
            valori[i] = nomi[i].length();
        }

        System.out.println("\nDiagramma Orizzontale:");
        stampaDiagrammaOrizzontale(nomi, valori);

        System.out.println("\nDiagramma Verticale:");
        stampaDiagrammaVerticale(nomi, valori);


    }


    public static void stampaDiagrammaOrizzontale(String[] nomi, int[] valori) {
        final int maxAsterischi = 40;

        double maxValore = 0;
        for (int valore : valori) {
            if (valore > maxValore) {
                maxValore = valore;
            }
        }

        double proporzione = maxAsterischi / maxValore;

        for (int i = 0; i < nomi.length; i++) {
            String nome = nomi[i];
            int valore = valori[i];
            int asterischi = (int) (valore * proporzione);

            StringBuilder stampaAsterichi = new StringBuilder();
            for (int j = 0; j < asterischi; j++) {
                stampaAsterichi.append("*");
            }
            System.out.printf("%-15s %s%n", nome, stampaAsterichi);
        }
    }


    public static void stampaDiagrammaVerticale(String[] nomi, int[] valori) {
        int maxValore = 0;
        final int maxAsterischi = 20;

        for (int valore : valori) {
            if (valore > maxValore) {
                maxValore = valore;
            }
        }

        double proporzione = (double) maxAsterischi / maxValore;

        for (int i = 0; i < maxAsterischi; i++) {
            for (int j = 0; j < nomi.length; j++) {
                int asterischi = (int) (valori[j] * proporzione);
                if (maxAsterischi - 1 - i < asterischi) {
                    System.out.printf("%-12s", "*");
                }
            }
            System.out.println();
        }
        for (String nome : nomi) {
            System.out.printf("%-12s", nome);
        }
        System.out.println();
    }

}

