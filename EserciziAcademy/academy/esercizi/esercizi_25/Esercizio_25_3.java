package academy.esercizi.esercizi_25;

public class Esercizio_25_3 {

    public static void main(String[] args) {
        Esercizio_25_3 esercizio_25_3 = new Esercizio_25_3();
        esercizio_25_3.soluzione();
    }

    public void soluzione(){
        int[] combinazioneAttuale = {1, 7, 2, 9};
        int[] combinazioneSblocco = {5, 7, 1, 4};


        for (int i = 0; i < 4; i++) {
            int attualeValore = combinazioneAttuale[i];
            int sbloccoValore = combinazioneSblocco[i];

            int twistSu = 0;
            int twistGiu = 0;

            if (sbloccoValore >= attualeValore) {
                twistSu = sbloccoValore - attualeValore;
            } else {
                twistSu = (10 - attualeValore) + sbloccoValore;
            }

            if (attualeValore >= sbloccoValore) {
                twistGiu = attualeValore - sbloccoValore;
            } else {
                twistGiu = (10 - sbloccoValore) + attualeValore;
            }

            if (twistSu < twistGiu) {
                if (twistSu == 1) {
                    System.out.printf("Anello %d: Gira una volta su %n", i + 1);
                } else {
                    System.out.printf("Anello %d: Gira su per %d volte%n", i + 1, twistSu);
                }
            } else {
                if (twistGiu == 1) {
                    System.out.printf("Anello %d: Gira una volta giu %n", i + 1);
                } else {
                    System.out.printf("Anello %d: Gira giu per %d volte%n", i + 1, twistGiu);
                }
            }
        }
    }
}
