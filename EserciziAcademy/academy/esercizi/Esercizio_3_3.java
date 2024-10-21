package academy.esercizi;

import java.util.Random;

public class Esercizio_3_3 {
    public static void main(String[] args) {
        String[][] tris = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };
        tris[0][2] = "X";
        riempiTrisCasualmente(tris);
        stampaTris(tris);
        stampaDiagonale(tris);
    }


    public static void stampaTris(String[][] tris) {
        for (int i = 0; i < tris.length; i++) {
            System.out.println(" " + tris[i][0] + " | " + tris[i][1] + " | " + tris[i][2]);
            if (i < tris.length - 1) {
                System.out.println("---|---|---");
            }
        }
    }

    public static void riempiTrisCasualmente(String[][] tris) {
        Random rand = new Random();
        for (int i = 0; i < tris.length; i++) {
            for (int j = 0; j < tris[i].length; j++) {
                if (tris[i][j].equals(" ")) {
                    tris[i][j] = rand.nextBoolean() ? "X" : "O";
                }
            }
        }
    }

    public static void stampaDiagonale(String[][] tris) {
        System.out.print("Diagonale: ");
        for (int i = 0; i < tris.length; i++) {
            System.out.print(tris[i][i] + " ");
        }
        System.out.println();
    }

}
