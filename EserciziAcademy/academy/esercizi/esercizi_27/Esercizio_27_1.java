package academy.esercizi.esercizi_27;

import java.util.Scanner;

public class Esercizio_27_1 {
    public static void main(String[] args) {
        Esercizio_27_1 esercizio_27 = new Esercizio_27_1();
        esercizio_27.soluzione();
    }

    static Scanner scanner = new Scanner(System.in);

    public void soluzione() {
        final int dimensioneMatrice = 4;
        int[][] matrice = new int[dimensioneMatrice][dimensioneMatrice];
        boolean[] numeriDuplicati = new boolean[16];
        System.out.println("Inserisci 16 numeri da 1 a 16:");

        for (int i = 0; i < dimensioneMatrice; i++) {
            for (int j = 0; j < dimensioneMatrice; j++) {
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
        for (int j = 0; j < dimensioneMatrice; j++) {
            somma += matrice[0][j];
        }
        boolean quadratoMagico = true;

        for (int i = 0; i < dimensioneMatrice; i++) {
            int sommaRiga = 0;
            for (int j = 0; j < dimensioneMatrice; j++) {
                sommaRiga += matrice[i][j];
            }
            if (sommaRiga != somma) {
                quadratoMagico = false;
            }
        }

        for (int j = 0; j < dimensioneMatrice; j++) {
            int sommaColonna = 0;
            for (int i = 0; i < dimensioneMatrice; i++) {
                sommaColonna += matrice[i][j];
            }
            if (sommaColonna != somma) {
                quadratoMagico = false;
            }
        }

        int sommaDiagonale1 = 0;
        int sommaDiagonale2 = 0;
        for (int i = 0; i < dimensioneMatrice; i++) {
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



