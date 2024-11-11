package academy.esercizi.utility;

import java.util.Random;

public class RandomNumber {

    public static int generaNumeroCasuale(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
