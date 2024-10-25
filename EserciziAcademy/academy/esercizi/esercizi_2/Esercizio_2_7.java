package academy.esercizi.esercizi_2;

import java.util.Arrays;

public class Esercizio_2_7 {
    public static void main(String[] args) {
        Esercizio_2_7 esercizio_2_7 = new Esercizio_2_7();
        esercizio_2_7.soluzione();
    }

    public void soluzione(){
        int[] arrayDiDieciInteri = new int[10];

        for (int i = 0; i < 6; i++) {
            arrayDiDieciInteri[i] = i + 1;
        }

        System.out.println("Array: " + Arrays.toString(arrayDiDieciInteri));

        int posizione = 2;
        int nuovoElemento = 99;

        arrayDiDieciInteri[posizione] = nuovoElemento;

        System.out.println("Array dopo l'inserimento: " + Arrays.toString(arrayDiDieciInteri));
    }
}
