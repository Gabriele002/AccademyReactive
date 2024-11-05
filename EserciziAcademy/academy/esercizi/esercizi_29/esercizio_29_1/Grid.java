package academy.esercizi.esercizi_29.esercizio_29_1;


import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Grid {
    private static final int RIGHE = 5;
    private static final int COLONNE = 8;


    public void avviaGioco() {
        int[][] tabella = new int[RIGHE][COLONNE];
        inizializzaTabella(tabella);
        stampaTabella(tabella);
        popolaCarte();

    }

    private static void stampaTabella(int[][] tabella) {
        System.out.println("----------------");
        for (int[] riga : tabella) {
            System.out.print("|");
            for (int cella : riga) {
                System.out.print(cella);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("----------------");
        }
    }

    private static void inizializzaTabella(int[][] tabella) {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                tabella[i][j] = tabella[2][2];
            }
        }
    }


    private static void popolaCarte() {
        Random random = new Random();
        int numeroCoppie = (RIGHE * COLONNE) / 2;
        Tile[] carte = new Tile[(RIGHE * COLONNE)];
        for (int i = 0; i < numeroCoppie; i++) {
            carte[2 * i] = new Tile(i + 1);
            carte[2 * i + 1] = new Tile(i + 1);
        }
        System.out.println(Arrays.toString(carte));

        for (int i = 0; i < carte.length; i++) {
            int indiceRandom = random.nextInt(carte.length);
            carte[i] = carte[indiceRandom];
        }
        System.out.println(Arrays.toString(carte));
    }
}
