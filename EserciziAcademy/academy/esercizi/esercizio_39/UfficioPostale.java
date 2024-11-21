package academy.esercizi.esercizio_39;

import java.util.ArrayList;
import java.util.List;

public class UfficioPostale {
    private List<Sportello> sportelli = new ArrayList<>();

    public UfficioPostale(List<String> nomi) {
        for (String nome : nomi) {
            this.sportelli.add(new Sportello(nome));
        }
    }

    public Sportello getSportelloLibero() {
        while (true) {
            synchronized (sportelli) {
                for (Sportello sportello : sportelli) {
                    if (sportello.isLibero()){
                        sportello.cambiaStato();
                        return sportello;
                    }
                }
            }
        }
    }
}
