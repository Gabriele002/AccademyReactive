package academy.esercizi.esercizi_2;

import java.util.Arrays;

public class Esercizio_2_9 {
    public static void main(String[] args) {
        Esercizio_2_9 esercizio_2_9 = new Esercizio_2_9();
        esercizio_2_9.soluzione();
    }


    public static void soluzione(){
        int[] arrayDiInteri = {1, 2, 3, 4, 5, 6, 7, 8, 9,10};
        soluzionePari(arrayDiInteri);
        int[] arrayDiInteriDispari = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        soluzioneDispari(arrayDiInteriDispari);
    }

    public static void soluzionePari(int[] arrayDiInteri) {

        int meta = arrayDiInteri.length / 2;
        int[] risultato = new int[arrayDiInteri.length];
        for (int i = 0; i < meta; i++) {
            risultato[i] = arrayDiInteri[meta + i];
        }
        for (int i = meta; i < arrayDiInteri.length; i++) {
            risultato[i] = arrayDiInteri[i - meta];
        }
        System.out.println(Arrays.toString(risultato));
    }

    public static void soluzioneDispari(int[] arrayDiInteri) {
        int meta = arrayDiInteri.length / 2;
        int[] risultato = new int[arrayDiInteri.length];
        risultato[meta] = arrayDiInteri[meta];
        for (int i = 0; i < meta; i++) {
            risultato[i + meta + 1] = arrayDiInteri[i];
        }
        for (int i = meta + 1; i < arrayDiInteri.length; i++) {
            risultato[i - meta - 1] = arrayDiInteri[i];
        }

        System.out.println(Arrays.toString(risultato));
    }

}

