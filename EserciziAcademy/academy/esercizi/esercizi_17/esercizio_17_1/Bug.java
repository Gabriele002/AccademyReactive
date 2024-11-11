package academy.esercizi.esercizi_17.esercizio_17_1;


public class Bug {

    private int posizioneIniziale;
    private int posizione;
    private Direzione direzione;
    private String[] percorso;
    private int passo;

    public Bug(int posizioneIniziale) {
        this.posizioneIniziale = posizioneIniziale;
        this.posizione = posizioneIniziale;
        this.direzione = Direzione.DESTRA;
        this.percorso = new String[10];
        this.passo = 0;
        registraPercorso();
    }

    public void move() {
        if (direzione == Direzione.DESTRA) {
            posizione++;
            registraPercorso();
        } else {
            posizione--;
            registraPercorso();
        }
    }

    public void gira() {
        if (direzione == Direzione.DESTRA) {
            direzione = Direzione.SINISTRA;
        } else {
            direzione = Direzione.DESTRA;
        }
    }

    private void registraPercorso() {
        if (passo < percorso.length) {
            percorso[passo] = "Posizione: " + posizione + " Direzione: " + direzione;
            passo++;
        }
    }

    public void stampaPercorso() {
        System.out.println("Percorso del bug:");
        for (int i = 0; i < passo; i++) {
            System.out.println(percorso[i]);
        }
    }
}

