package academy.esercizi;

import java.util.Arrays;

public class Esercizio_2_6 {
    public static void main(String[] args) {
        int [] numeri = {1,2,3,4,5,6,7,8,9,10};
        int posizioneDaRimuovere = 3;
        int[] arrayModificato = rimuoviElemento(numeri, posizioneDaRimuovere);

        System.out.println("Array originale: " + Arrays.toString(numeri));
        System.out.println("Array modificato: " + Arrays.toString(arrayModificato));
    }

    public static int[] rimuoviElemento(int[] array, int posizione) {
        int[] nuovoArray = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != posizione) {
                nuovoArray[j++] = array[i];
            }
        }
        return nuovoArray;
    }

}
