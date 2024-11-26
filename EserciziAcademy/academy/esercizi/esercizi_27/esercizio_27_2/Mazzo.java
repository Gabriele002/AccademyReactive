package academy.esercizi.esercizi_27.esercizio_27_2;

import java.util.Random;

public class Mazzo {
    final int numeroDiCarte = 52;
    private final Carta[] carte;
    private int indiceCarta;

    public Mazzo() {
        String[] semi = {"Cuori", "Fiori", "Picche", "Quadri"};
        int[] valori = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        carte = new Carta[numeroDiCarte];
        int indiceCarta = 0;

        for (String seme : semi) {
            for (int valore : valori) {
                carte[indiceCarta++] = new Carta(seme, valore);
            }
        }
        mescola();
    }

    public void mescola() {
        Random random = new Random();
        for (int i = 0; i < carte.length; i++) {
            int posizioneRandom = random.nextInt(carte.length);
            Carta cartaRandom = carte[i];
            carte[i] = carte[posizioneRandom];
            carte[posizioneRandom] = cartaRandom;
        }
    }

    public Carta[] distribuisci(int numero) {
        Carta[] carteDistribuite = new Carta[numero];
        for (int i = 0; i < numero; i++) {
            if (indiceCarta < carte.length) {
                carteDistribuite[i] = carte[indiceCarta++];
            }
        }
        return carteDistribuite;
    }
}

