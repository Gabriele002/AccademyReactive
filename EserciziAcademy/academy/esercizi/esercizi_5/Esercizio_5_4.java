package academy.esercizi.esercizi_5;


import java.util.Scanner;

public class Esercizio_5_4 {
    public static void main(String[] args) {
        Esercizio_5_4 esercizio_5_4 = new Esercizio_5_4();
        esercizio_5_4.soluzione();
    }

    public void soluzione() {
        Scanner scn = new Scanner(System.in);
        int numeroDallUtente;

        do {
            System.out.println("Inseriscimi un valore compreso tra 0 e 100");
            numeroDallUtente = scn.nextInt();
        } while (numeroDallUtente > 100 || numeroDallUtente < 0);
    }
}
