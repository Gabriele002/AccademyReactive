package academy.esercizi.esercizi_3;

public class Esercizio_3_1 {

    public static void main(String[] args) {
        int[][] tabellone = new int[9][10];
        popolaTabellone(tabellone);
        stampaTabellone(tabellone);

        trovaNumeriVicini(tabellone, 8, 8);
        trovaCoordinate(tabellone, 5);
    }

    public static void popolaTabellone(int[][] tabellone) {
        int numero = 1;
        for (int i = 0; i < tabellone.length; i++) {
            for (int j = 0; j < tabellone[i].length; j++) {
                if (numero <= 90) {
                    tabellone[i][j] = numero;
                    numero++;
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

    public static void trovaNumeriVicini(int[][] tabellone, int x, int y) {
        if (x < 0 || x >= tabellone.length || y < 0 || y >= tabellone[0].length) {
            System.out.println("Coordinate non valide!");
        }

        System.out.println("Numeri vicini al numero " + tabellone[x][y] + ":");

        if (y - 1 >= 0) {
            System.out.println("Numero alla sinistra: " + tabellone[x][y - 1]);
        } else {
            System.out.println("Numero alla sinistra: Non presente");
        }

        if (y + 1 < tabellone[0].length) {
            System.out.println("Numero alla destra: " + tabellone[x][y + 1]);
        } else {
            System.out.println("Numero alla destra: Non presente");
        }

        if (x - 1 >= 0) {
            System.out.println("Numero in alto: " + tabellone[x - 1][y]);
        } else {
            System.out.println("Numero in alto: Non presente");
        }

        if (x + 1 < tabellone.length) {
            System.out.println("Numero in basso: " + tabellone[x + 1][y]);
        } else {
            System.out.println("Numero in basso: Non presente");
        }
    }

    public static void trovaCoordinate(int[][] tabellone, int numero) {
        int x = -1;
        int y = -1;

        for (int i = 0; i < tabellone.length; i++) {
            for (int j = 0; j < tabellone[i].length; j++) {
                if (tabellone[i][j] == numero) {
                    x = i;
                    y = j;
                }
            }
        }
        if (x == -1 || y == -1) {
            System.out.println("Numero " + numero + " non trovato nel tabellone!");
        } else {
            trovaNumeriVicini(tabellone, x, y);
        }
    }

}
