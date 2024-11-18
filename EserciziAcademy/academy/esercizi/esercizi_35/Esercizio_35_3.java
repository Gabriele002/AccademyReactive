package academy.esercizi.esercizi_35;

import java.util.Scanner;

public class Esercizio_35_3 {
    public static void main(String[] args) {
        Esercizio_35_3 esercizio_35_3 = new Esercizio_35_3();
        esercizio_35_3.soluzione();
    }

    private void soluzione() {
        try (Scanner scn = new Scanner(System.in)) {
            System.out.println("Inseriscimi un numero in stringa");
            int numero = Integer.parseInt(scn.next());
            System.out.println(numero);
        } catch (NumberFormatException e) {
            System.out.println("Il valore che provi a convertire non e valido: " + e);
        }
    }
}
