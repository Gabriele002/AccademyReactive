package academy.esercizi.esercizi_32.Esercizio_32_3;

public class Appuntamento {
    private String descrizione;


    public Appuntamento(String descrizione) {
        this.descrizione = descrizione;
    }

    public Appuntamento aggiungiAppuntamento(){
        return null;
    }

    public void occursOn (int anno, int mese, int giorno){

    }

    @Override
    public String toString() {
        return "Appuntamento " +
                "descrizione '" + descrizione;
    }

}
