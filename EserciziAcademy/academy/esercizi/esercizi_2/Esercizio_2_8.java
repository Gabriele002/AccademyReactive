package academy.esercizi.esercizi_2;

import java.util.ArrayList;

public class Esercizio_2_8 {
    public static void main(String[] args) {
        java.util.Random random = new java.util.Random();

        int numero1 = 0;
        int numero2 = 0;
        int numero3 = 0;
        int numero4 = 0;
        int numero5 = 0;
        int numero6 = 0;

        for (int i = 0; i < 1000; i++) {
            int lancioDado = random.nextInt(6) + 1;
            switch (lancioDado) {
                case 1:
                    numero1++;
                    break;
                case 2:
                    numero2++;
                    break;
                case 3:
                    numero3++;
                    break;
                case 4:
                    numero4++;
                    break;
                case 5:
                    numero5++;
                    break;
                case 6:
                    numero6++;
                    break;
            }
        }

        System.out.println("Lancio del dado con risultato 1: " + numero1);
        System.out.println("Lancio del dado con risultato 2: " + numero2);
        System.out.println("Lancio del dado con risultato 3: " + numero3);
        System.out.println("Lancio del dado con risultato 4: " + numero4);
        System.out.println("Lancio del dado con risultato 5: " + numero5);
        System.out.println("Lancio del dado con risultato 6: " + numero6);
    }
}
