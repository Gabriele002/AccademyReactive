package academy.esercizi.esercizi_38.esercizio_38_1;

import java.util.Optional;

public class GiocoDelSegnoOptional {

    private ElementoCasuale elementoCasuale;
    private int turno;
    int punteggio;


    public GiocoDelSegnoOptional() {
        this.elementoCasuale = new ElementoCasuale();
        this.turno = 1;
    }

    public boolean gioco(Optional<Integer> valoreUtente) {
        if (valoreUtente.equals(elementoCasuale.getValore())) {
            System.out.println("Hai vintoooo");
            calcolaPunti();
            return true;
        } else if (!valoreUtente.isPresent() && elementoCasuale.getValore().isPresent()) {
            if (turno > 3) {
                System.out.println("Hai perso");
                punteggio = -10;
                System.out.println("Il punteggio finale e': " + punteggio);
                return true;
            }
        } else if (elementoCasuale.getValore().orElse(0).equals(1)) {
            System.out.println("Hai perso");
            calcolaPunti();
            return true;
        }
        elementoCasuale.setValore(elementoCasuale.getValore().map(val -> val / 2));
        turno++;
        return false;
    }

    public void calcolaPunti() {
        punteggio = 0;

        if (!elementoCasuale.getValore().isPresent()) {
            if (turno <= 3) {
                punteggio = 2;
            } else {
                if (turno > 4) {
                    punteggio = -10;
                } else {
                    punteggio = 0;
                }
            }
        } else {
            if (turno == 1) {
                punteggio = 1000;
            } else if (turno == 2 || turno == 3) {
                punteggio = 100;
            } else if (turno >= 4 && turno <= 6) {
                punteggio = 10;
            } else if (turno > 6) {
                punteggio = 5;
            } else {
                if (elementoCasuale.getValore().get() == 1) {
                    punteggio = -1;
                }
            }
        }

        System.out.println("Punteggio: " + punteggio);
    }

}
