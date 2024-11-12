package academy.esercizi.esercizi_24;

import academy.esercizi.utility.RandomNumber;

import java.util.Arrays;

public class Esercizio_24_2 {
    public static void main(String[] args) {
        Esercizio_24_2 esercizio_24_2 = new Esercizio_24_2();
        esercizio_24_2.soluzione();
    }

    private void soluzione() {
        int[] arrayDiInteri = new int[100];
        for (int i = 0; i < arrayDiInteri.length; i++) {
            arrayDiInteri[i] = RandomNumber.generaNumeroCasuale(0, arrayDiInteri.length);
            for(int j = 0; j < i; j++ ) {
                if (arrayDiInteri[j] == arrayDiInteri[i]){
                    arrayDiInteri[i] = RandomNumber.generaNumeroCasuale(0, arrayDiInteri.length);
                    j = 0;
                }
            }
        }
        Arrays.sort(arrayDiInteri);
        for (int numero : arrayDiInteri) {
            System.out.println(numero);
        }
    }
}
