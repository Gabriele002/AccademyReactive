package academy.esercizi.esercizi_4;

import academy.esercizi.utility.Validificazione;

import java.util.Scanner;

public class Esercizio_4_3 {
    public static void main(String[] args) {
        Esercizio_4_3 esercizio_4_3 = new Esercizio_4_3();
        esercizio_4_3.soluzione();
    }

    public void soluzione(){

        Scanner scn = new Scanner(System.in);
        System.out.println("Inserisci un numero");
        int numeroUtante = scn.nextInt();
        Validificazione.validificaIntMinoreDiZero(numeroUtante, "Il numero deve essere maggiore di zero");
        int somma = 0;
        for (int i = 1; i <= numeroUtante; i++) {
            somma += i;
        }
        System.out.println("La somma dei numeri interi da 1 a " + numeroUtante + " : " + somma);
    }
}
