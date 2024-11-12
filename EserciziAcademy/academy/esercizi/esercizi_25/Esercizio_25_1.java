package academy.esercizi.esercizi_25;

import academy.esercizi.esercizi_17.esercizio_17_1.Direzione;
import academy.esercizi.utility.RandomNumber;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;

public class Esercizio_25_1 {

    private static final int x = 8;
    private static final int y = 12;

    public static void main(String[] args) {
        Esercizio_25_1 esercizio_25_1 = new Esercizio_25_1();
        esercizio_25_1.soluzione();
    }


    private void soluzione() {
        String[][] incrocio = new String[x][y];
        int[] posizioneUbriaco = {0,0};

        for (int i = 0; i < 100; i++) {
            movimentoUbriaco(posizioneUbriaco);
            stampaIncrocio(incrocio, posizioneUbriaco);
        }
    }

    private void stampaIncrocio(String[][] incrocio, int[] posizioneUbriaco) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == posizioneUbriaco[0] && j == posizioneUbriaco[1]){
                    System.out.printf("%2s", "U");
                } else {
                    System.out.printf("%2s", "■");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }


    private void movimentoUbriaco(int[] posizioneUbriaco) {
        Random random = new Random();
        DIREZIONE direzione = DIREZIONE.values()[random.nextInt(4)];
        switch (direzione){
            case SU:{
                if (posizioneUbriaco[0] == 0){
                    System.out.println("Hai preso in muro in alto");
                } else {
                    posizioneUbriaco[0] -= 1;
                }
                break;
            }
            case GIU:{
                if (posizioneUbriaco[0] == x - 1){
                    System.out.println("Hai preso il muro in basso");
                } else {
                    posizioneUbriaco[0] += 1;
                }
                break;
            }
            case DESTRA:{
                if (posizioneUbriaco[1] == y - 1){
                    System.out.println("Hai preso il muro di destra");
                } else {
                    posizioneUbriaco[1] += 1;
                }
                break;
            }
            case SINISTRA:{
                if (posizioneUbriaco[1] == 0){
                    System.out.println("Hai preso il muro di sinistra");
                } else {
                    posizioneUbriaco[1] -= 1;
                }
                break;
            }
        }
    }

    enum DIREZIONE {
        SU,
        GIU,
        DESTRA,
        SINISTRA;
    }
}

