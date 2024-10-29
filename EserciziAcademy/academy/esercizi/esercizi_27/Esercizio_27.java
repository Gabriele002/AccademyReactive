package academy.esercizi.esercizi_27;

import java.util.Scanner;

public class Esercizio_27 {
    public static void main(String[] args) {
        Esercizio_27 esercizio_27 = new Esercizio_27();
        esercizio_27.soluzione();
    }

    static Scanner scanner = new Scanner(System.in);

    public void soluzione() {
        int[][] matrice = new int[4][4];
        boolean[] numeriDuplicati = new boolean[16];
        System.out.println("Inserisci 16 numeri da 1 a 16:");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int numero;
                do {
                    System.out.print("Inserisci il numero per la posizione [" + i + "][" + j + "]: ");
                    numero = scanner.nextInt();
                    if (numero < 1 || numero > 16) {
                        System.out.println("Numero non valido. Devi inserire numeri tra 1 e 16.");
                    } else if (numeriDuplicati[numero - 1]) {
                        System.out.println("Numero duplicato. Inserisci un altro numero.");
                    }
                } while (numero < 1 || numero > 16 || numeriDuplicati[numero - 1]);
                numeriDuplicati[numero - 1] = true;
                matrice[i][j] = numero;
            }
        }

        int somma = 0;
        for (int j = 0; j < 4; j++) {
            somma += matrice[0][j];
        }

        boolean quadratoMagico = true;

        for (int i = 0; i < 4; i++) {
            int sommaRiga = 0;
            for (int j = 0; j < 4; j++) {
                sommaRiga += matrice[i][j];
            }
            if (sommaRiga != somma) {
                quadratoMagico = false;
            }
        }

        for (int j = 0; j < 4; j++) {
            int sommaColonna = 0;
            for (int i = 0; i < 4; i++) {
                sommaColonna += matrice[i][j];
            }
            if (sommaColonna != somma) {
                quadratoMagico = false;
            }
        }

        int sommaDiagonale1 = 0;
        int sommaDiagonale2 = 0;
        for (int i = 0; i < 4; i++) {
            sommaDiagonale1 += matrice[i][i];
            sommaDiagonale2 += matrice[i][3 - i];
        }

        if (sommaDiagonale1 != somma || sommaDiagonale2 != somma) {
            quadratoMagico = false;
        }

        if (quadratoMagico) {
            System.out.println("La matrice è un quadrato magico!");
        } else {
            System.out.println("La matrice non è un quadrato magico.");
        }
    }
}



