package academy.esercizi.esercizi_2;


import java.util.Scanner;

public class Esercizio_2_3 {
    public static void main(String[] args) {
        Esercizio_2_3 esercizio_2_3 = new Esercizio_2_3();
        esercizio_2_3.soluzione();
    }
    public void soluzione(){
        Scanner scn = new Scanner(System.in);

        int valore;
        do {
            System.out.println("Inserisci un valore compreso tra 0 e 100: ");
            valore = scn.nextInt();
        } while (valore < 0 || valore > 100);
        System.out.printf("Hai inserito un valore valido: %d", valore );
    }
}
