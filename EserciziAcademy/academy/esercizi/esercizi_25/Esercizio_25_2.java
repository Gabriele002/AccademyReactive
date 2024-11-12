package academy.esercizi.esercizi_25;

import academy.esercizi.utility.RandomNumber;


public class Esercizio_25_2 {

    private final int iterazioni = 1000000;
    public static void main(String[] args) {
        Esercizio_25_2 esercizio_25_2= new Esercizio_25_2();
        esercizio_25_2.soluzione();

    }

    private void soluzione() {
        int numeroDiVolteSei = 0;
        for (int i = 0; i < iterazioni; i++) {
            int lancioDado = RandomNumber.generaNumeroCasuale(1,6);
            if (lancioDado == 6){
                numeroDiVolteSei++;
            }

        }
        System.out.println("numeroDiVolteSei = " + numeroDiVolteSei);


        int numeroDiDoppioSei = 0;
        for (int i = 0; i < iterazioni; i++) {
            int lancioDado = RandomNumber.generaNumeroCasuale(1,6);
            int lancioDado2 = RandomNumber.generaNumeroCasuale(1,6);
            if (lancioDado == 6 && lancioDado2 == 6){
                numeroDiDoppioSei++;
            }

        }
        System.out.println("numeroDiDoppioSei = " + numeroDiDoppioSei);

    }
}
