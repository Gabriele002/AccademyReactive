package academy.esercizi;

import java.util.ArrayList;

public class Esercizio_2_8 {
    public static void main(String[] args) {
        java.util.Random random = new java.util.Random();

        ArrayList<Integer> numero1 = new ArrayList<>();
        ArrayList<Integer> numero2 = new ArrayList<>();
        ArrayList<Integer> numero3 = new ArrayList<>();
        ArrayList<Integer> numero4 = new ArrayList<>();
        ArrayList<Integer> numero5 = new ArrayList<>();
        ArrayList<Integer> numero6 = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            int lancioDado = random.nextInt(6) + 1;
            switch (lancioDado) {
                case 1:
                    numero1.add(lancioDado);
                    break;
                case 2:
                    numero2.add(lancioDado);
                    break;
                case 3:
                    numero3.add(lancioDado);
                    break;
                case 4:
                    numero4.add(lancioDado);
                    break;
                case 5:
                    numero5.add(lancioDado);
                    break;
                case 6:
                    numero6.add(lancioDado);
                    break;
            }
        }

        System.out.println("Lancio del dado con risultato 1: " + numero1.size());
        System.out.println("Lancio del dado con risultato 2: " + numero2.size());
        System.out.println("Lancio del dado con risultato 3: " + numero3.size());
        System.out.println("Lancio del dado con risultato 4: " + numero4.size());
        System.out.println("Lancio del dado con risultato 5: " + numero5.size());
        System.out.println("Lancio del dado con risultato 6: " + numero6.size());
    }
}
