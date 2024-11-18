package academy.esercizi.esercizi_35;

import java.util.Scanner;

public class Esercizio_35_2 {
    public static void main(String[] args) {
        Esercizio_35_2 esercizio_35_2 = new Esercizio_35_2();
        esercizio_35_2.soluzione();
    }

    private void soluzione() {
        int[] arrayDiInteri = {1, 2, 3, 4, 5, 6};
        try (Scanner scn = new Scanner(System.in)) {
            System.out.println("Inseriscimi l indice di un array");
            int numero = arrayDiInteri[scn.nextInt()];
            System.out.println(numero);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("L indice che hai inserito non e valido: " + e);
        }
    }
}
