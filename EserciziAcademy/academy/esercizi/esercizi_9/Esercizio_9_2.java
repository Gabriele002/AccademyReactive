package academy.esercizi.esercizi_9;

import java.util.Scanner;

public class Esercizio_9_2 {
    public static void main(String[] args) {
        Esercizio_9_2 esercizio_9_2 = new Esercizio_9_2();
        esercizio_9_2.soluzione();
    }
//    fl.oz (once fluide) → mllitri (l)
//    gal (galloni) → litri (l)
//    oz (once) → grammi (g) (o chilogrammi, a seconda del contesto)
//    lb (libbre) → chilogrammi (kg)
//    in (pollici) → centimetri (cm) (o metri, a seconda del contesto)
//    ft (piedi) → metri (m)
//    mi (miglia) → chilometri (km)

    public void soluzione(){
        Scanner scn = new Scanner(System.in);

        System.out.print("Scegli l'unità di partenza (fl.oz, gal, oz, lb, in, ft, mi): ");
        String unitaPartenza = scn.nextLine();

        System.out.print("Scegli l'unità di destinazione (ml, l, g, kg, mm, cm, m, km): ");
        String unitaDestinazione = scn.nextLine();

        System.out.print("Inserisci il valore da convertire: ");
        double valoreDaConvertire = scn.nextDouble();



        double risultato = 0;
        boolean conversioneValida = true;

        if (unitaPartenza.equals("fl.oz") && unitaDestinazione.equals("ml")) {
            risultato = valoreDaConvertire * 29.5735;
        } else if (unitaPartenza.equals("gal") && unitaDestinazione.equals("l")) {
            risultato = valoreDaConvertire * 3.78541;
        } else if (unitaPartenza.equals("oz") && unitaDestinazione.equals("g")) {
            risultato = valoreDaConvertire * 28.3495;
        } else if (unitaPartenza.equals("lb") && unitaDestinazione.equals("kg")) {
            risultato = valoreDaConvertire * 0.453592;
        } else if (unitaPartenza.equals("in") && unitaDestinazione.equals("cm")) {
            risultato = valoreDaConvertire * 2.54;
        } else if (unitaPartenza.equals("ft") && unitaDestinazione.equals("m")) {
            risultato = valoreDaConvertire * 0.3048;
        } else if (unitaPartenza.equals("mi") && unitaDestinazione.equals("km")) {
            risultato = valoreDaConvertire * 1.60934;
        } else {
            conversioneValida = false;
        }

        if (conversioneValida) {
            System.out.printf("Risultato: %.2f %s%n", risultato, unitaDestinazione);
        } else {
            System.out.println("Conversione incompatibile.");
        }

    }


}
