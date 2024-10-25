package academy.esercizi.esercizi_5;

import academy.esercizi.utility.Validificazione;

import java.util.Scanner;

public class Esercizio_5_1 {
    public static void main(String[] args) {
        Esercizio_5_1 esercizio_5_1 = new Esercizio_5_1();
        esercizio_5_1.soluzione();
    }

    public void soluzione() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Quanti valori vuoi inserire");
        int durataCiclo = scn.nextInt();
        Validificazione.validificaIntMinoreDiZero(durataCiclo, "Il numero non puo essere zero");

        int tot = 0;

        for (int i = 0; i < durataCiclo; i++) {
            System.out.println("Inseriscimi i numero da sommare");
            int valoreInput = scn.nextInt();
            tot += valoreInput;
        }

        System.out.println("Il totale dei numeri inseriti e " + tot);
    }
}
