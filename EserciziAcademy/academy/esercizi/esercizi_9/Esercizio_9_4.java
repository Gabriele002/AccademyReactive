package academy.esercizi.esercizi_9;

import java.util.Scanner;

public class Esercizio_9_4 {

    public static void main(String[] args) {
        Esercizio_9_4 esercizio_9_4 = new Esercizio_9_4();
        esercizio_9_4.soluzione();
    }

    public void soluzione(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Inserisci un anno:");
        int anno = scn.nextInt();

        boolean bisestile = (anno % 4 == 0 && (anno % 100 != 0 || anno % 400 == 0));

        if(bisestile) {
            System.out.println("L' anno e' bisestile");
        } else {
            System.out.println("L'anno non e' bisestile");
        }

    }
}
