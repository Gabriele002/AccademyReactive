package academy.esercizi.esercizi_2;

import java.util.Arrays;

public class Esercizio_2_9 {
    public static void main(String[] args) {
        Esercizio_2_9 esercizio_2_9 = new Esercizio_2_9();
        esercizio_2_9.soluzione();
    }

    public void soluzione(){
        int[] arrayDiInteri = {1,2,3,4,5,6,7,8,9,10};

        for (int i = 0; i < arrayDiInteri.length/2; i++) {
            int valoriMeta = arrayDiInteri[i];
            arrayDiInteri[i] = arrayDiInteri[arrayDiInteri.length/2 + i];
            arrayDiInteri[arrayDiInteri.length/2 + i] = valoriMeta;
        }
        System.out.println(Arrays.toString(arrayDiInteri));
    }

}

