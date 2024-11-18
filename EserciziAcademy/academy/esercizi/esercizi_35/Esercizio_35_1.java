package academy.esercizi.esercizi_35;

import java.util.Scanner;

public class Esercizio_35_1 {
    public static void main(String[] args) {
        Esercizio_35_1 esercizio_35_1 = new Esercizio_35_1();
        esercizio_35_1.soluzione();
    }

    private void soluzione() {
        dividiDueNumeri();
    }

    public static void dividiDueNumeri() {
        try (Scanner scn = new Scanner(System.in)) {
            System.out.println("Inserisci il primo numero:");
            int numero1 = scn.nextInt();
            System.out.println("Inserisci il secondo numero:");
            int numero2 = scn.nextInt();
            int risultato = numero1 / numero2;
            System.out.println("Il risultato della divisione è: " + risultato);
        } catch (ArithmeticException e) {
            System.out.println("Errore: non puoi dividere per zero.");
        }
    }
}
