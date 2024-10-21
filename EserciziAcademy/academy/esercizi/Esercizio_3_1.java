package academy.esercizi;

public class Esercizio_3_1 {


    public static void main(String[] args) {
        int[][] tabellone = new int[10][9];
        popolaTabellone(tabellone);
        stampaTabellone(tabellone);
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
                    System.out.printf("%4d ", tabellone[i][j]);
                }
            }
            System.out.println();
        }
    }


}
