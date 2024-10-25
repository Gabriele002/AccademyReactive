package academy.esercizi.esercizi_5;


import java.util.Locale;
import java.util.Scanner;

public class Esercizio_5_6 {
    public static void main(String[] args) {
        Esercizio_5_6 esercizio_5_6 = new Esercizio_5_6();
        esercizio_5_6.soluzione();
    }

    public void soluzione() {
        Scanner scn = new Scanner(System.in);
        Mesi[] mesi = Mesi.values();
        int[] temperature = new int[mesi.length];

        for (int i = 0; i < mesi.length; i++) {
            System.out.println("Inserisci la temperatura di " + mesi[i]);
            temperature[i] = scn.nextInt();
        }

        int temperaturaMassima = 0;
        String mesePiuCaldo = "";
        for (int i = 0; i < mesi.length; i++) {
            if (temperature[i] > temperature[temperaturaMassima]) {
                temperaturaMassima = temperature[i];
                mesePiuCaldo = mesi[i].toString();
            }
        }
       System.out.println("La temperatura massima registrata e' di " + temperaturaMassima + " gradi,del mese di: " + mesePiuCaldo.toLowerCase(Locale.ROOT));
    }

    enum Mesi {
        GENNAIO,
        FEBRAIO,
        MARZO,
        APRILE,
        MAGGIO,
        GIUGNO,
        LUGLIO,
        AGOSTO,
        SETTEMBRE,
        OTTOBRE,
        NOVEMBRE,
        DICEMBRE;
    }
}
