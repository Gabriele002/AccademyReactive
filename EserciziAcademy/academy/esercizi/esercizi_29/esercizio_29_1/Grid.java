package academy.esercizi.esercizi_29.esercizio_29_1;


import java.util.Random;

public class Grid {
    private Tile[][] tabella;
    protected static final int righe = 5;
    protected static final int colonne = 8;


    public Grid() {
        this.tabella = new Tile[righe][colonne];
        inizializzaTabella(tabella);
    }

    public static Tile[][] tabellaCompleta() {
        Tile[][] tabella = new Tile[righe][colonne];
        inizializzaTabella(tabella);
        return tabella;
    }

    public static void stampaTabella(Tile[][] tabella) {
        System.out.println("---------------------------------");
        for (Tile[] riga : tabella) {
            System.out.printf("%-2s", "|");
            for (Tile cella : riga) {
                System.out.printf("%-2s", cella);
                System.out.printf("%-2s", "|");
            }
            System.out.println();
            System.out.println("---------------------------------");
        }
    }

    private static void inizializzaTabella(Tile[][] tabella) {
        Tile[] carte = popolaCarte();
        int index = 0;
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                tabella[i][j] = carte[index];
                index++;
            }
        }
    }


    private static Tile[] popolaCarte() {
        Random random = new Random();
        int numeroCoppie = (righe * colonne) / 2;
        Tile[] carte = new Tile[(righe * colonne)];
        for (int i = 0; i < numeroCoppie; i++) {
            carte[2 * i] = new Tile(i + 1);
            carte[2 * i + 1] = new Tile(i + 1);
        }
        for (int i = 0; i < carte.length; i++) {
            int indiceRandom = random.nextInt(carte.length);
            Tile cartaDaSostiruireRandomicamente = carte[i];
            carte[i] = carte[indiceRandom];
            carte[indiceRandom] = cartaDaSostiruireRandomicamente;
        }
        return carte;
    }

    public static Tile[][] popolaTabellaConValoriFissi() {
        int[] valoriFissi = {
                1, 1, 2, 2, 3, 3, 4, 4,
                5, 5, 6, 6, 7, 7, 8, 8,
                9, 9, 10, 10, 11, 11, 12,12, 13,
                13, 14, 14, 15,15, 16, 16, 17, 17,
                18, 18, 19, 19, 20, 20
        };
        int index = 0;
        Tile [][] tabellaValoriFissi = new Tile[righe][colonne];
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                tabellaValoriFissi[i][j] = new Tile(valoriFissi[index]);
                index++;
            }
        }
        return tabellaValoriFissi;
    }
}

