package academy.esercizi.esercizi_15.eserczio_15_4;

public class Circuito {
    private StatoInteruttore primoInterruttore = StatoInteruttore.GIU;
    private StatoInteruttore secondoInterruttore = StatoInteruttore.GIU;
    private StatoLampadina lampadina = StatoLampadina.SPENTO;


    public void cambiaPrimoInterruttore() {
        primoInterruttore = (primoInterruttore == StatoInteruttore.SU) ? StatoInteruttore.GIU : StatoInteruttore.SU;
        aggiornaStatoLampadina();
    }

    public void cambiaSecondoInterruttore() {
        secondoInterruttore = (secondoInterruttore == StatoInteruttore.SU) ? StatoInteruttore.GIU : StatoInteruttore.SU;
        aggiornaStatoLampadina();
    }

    private void aggiornaStatoLampadina() {
        int conteggioInterruttoriSu = (primoInterruttore == StatoInteruttore.SU ? 1 : 0) + (secondoInterruttore == StatoInteruttore.SU ? 1 : 0);
        lampadina = (conteggioInterruttoriSu % 2 == 0) ? StatoLampadina.SPENTO : StatoLampadina.ACCESSO;
    }


    @Override
    public String toString() {
        return "Circuito " +
                "primoInterruttore=" + primoInterruttore +
                ", secondoInterruttore=" + secondoInterruttore +
                ", lampadina=" + lampadina;
    }
}
