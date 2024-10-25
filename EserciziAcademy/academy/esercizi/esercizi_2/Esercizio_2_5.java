package academy.esercizi.esercizi_2;

import java.util.Scanner;

public class Esercizio_2_5 {
    public static void main(String[] args) {
        Esercizio_2_5 esercizio_2_5 = new Esercizio_2_5();
        esercizio_2_5.soluzione();
    }
    public void soluzione(){
        Scanner scn = new Scanner(System.in);
        int[] arrayDiCentoInteri = new int[100];

        for (int i = 0; i < arrayDiCentoInteri.length; i++) {
            System.out.println("Inserisci il valore del numero che vuoi inserire o 0 per uscire");
            int valore = scn.nextInt();
            if (valore == 0) {
                break;
            }
            arrayDiCentoInteri[i] = valore;
        }

        System.out.println("Hai inserito i seguenti numeri:");
        for (int elementi : arrayDiCentoInteri) {
            if (elementi != 0){
                System.out.println(elementi);
            }
        }

    }
}



