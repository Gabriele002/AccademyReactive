package academy.esercizi.esercizi_5;


import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Esercizio_5_8 {
    public static void main(String[] args) {
        Esercizio_5_8 esercizio_5_8 = new Esercizio_5_8();
        esercizio_5_8.soluzione();
    }

    public void soluzione() {
        int[] arrayDaUnoDieci = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arrayDaDieciVenti = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] arrayDaVentiTrenta = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

        int dimensioneArray = arrayDaUnoDieci.length + arrayDaDieciVenti.length + arrayDaVentiTrenta.length;
        int[] arrayCombinato = new int[dimensioneArray];

        int indiceArrayCombinato = 0;
        for (int numero : arrayDaUnoDieci) {
            arrayCombinato[indiceArrayCombinato++] = numero;
        }
        for (int numero : arrayDaDieciVenti) {
            arrayCombinato[indiceArrayCombinato++] = numero;
        }
        for (int numero : arrayDaVentiTrenta) {
            arrayCombinato[indiceArrayCombinato++] = numero;
        }
        System.out.println("Array combinato: " + Arrays.toString(arrayCombinato));
    }
}
