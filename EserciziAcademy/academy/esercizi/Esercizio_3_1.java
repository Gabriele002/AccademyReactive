package academy.esercizi;

import java.util.ArrayList;
import java.util.List;

public class Esercizio_3_1 {


    public static void main(String[] args) {
        int[][] tabellone = new int[9][10];
        popolaTabellone(tabellone);
        stampaTabellone(tabellone);

        List<Integer> vicini = trovaNumeriVicini(tabellone, 8, 8);
        System.out.println("Numeri vicini: " + vicini);

    }

    public static void popolaTabellone(int[][] tabellone) {
        int numero = 1;
        for (int i = 0; i < tabellone.length; i++) {
            for (int j = 0; j < tabellone[i].length; j++) {
                if (numero <= 90) {
                    tabellone[i][j] = numero;
                    numero++;
                } else {
                    tabellone[i][j] = 0;
                }
            }
        }
    }

    public static void stampaTabellone(int[][] tabellone) {
        for (int i = 0; i < tabellone.length; i++) {
            for (int j = 0; j < tabellone[i].length; j++) {
                if (tabellone[i][j] != 0) {
                    System.out.printf("%2d ", tabellone[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static List<Integer> trovaNumeriVicini(int[][] tabellone, int x, int y) {
        List<Integer> vicini = new ArrayList<>();

        if (x - 1 >= 0){
            vicini.add(tabellone[x - 1][y]);
        }

        if (x + 1 < tabellone.length){
            vicini.add(tabellone[x + 1][y]);
        }
        if (y - 1 >= 0){
            vicini.add(tabellone[x][y - 1]);
        }
        if (y + 1 < tabellone[0].length){
            vicini.add(tabellone[x][y + 1]);
        }

        if (vicini.isEmpty()) {
            vicini.add(-1);
        }

        return vicini;
    }
}
