package academy.esercizi.esercizi_5;

import academy.esercizi.utility.Validificazione;

import java.util.Scanner;

public class Esercizio_5_3 {
    public static void main(String[] args) {
        Esercizio_5_3 esercizio_5_3 = new Esercizio_5_3();
        esercizio_5_3.soluzione();
    }

    public void soluzione() {
       String prova = "Ciao Gabriele";

       boolean flag = true;
       int cont = 0;
       while (flag){
          char carattere= prova.charAt(cont);
           cont ++;
           if (carattere == ' '){
               System.out.println("Spazio trovato a carrattere "+ cont);
               flag = false;
           }
       }
    }
}
