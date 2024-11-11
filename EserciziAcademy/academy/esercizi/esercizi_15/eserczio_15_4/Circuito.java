package academy.esercizi.esercizi_15.eserczio_15_4;

public class Circuito {
    private StatoLampadina primoInterruttore;
    private StatoLampadina secondoInterruttore;
    private StatoLampadina lampadina;

    public Circuito() {
        this.primoInterruttore = StatoLampadina.SPENTO;
        this.secondoInterruttore = StatoLampadina.SPENTO;
        this.lampadina = StatoLampadina.SPENTO;
    }

    public void cambiaPrimoInterruttore() {
        this.primoInterruttore = (this.primoInterruttore == StatoLampadina.SPENTO) ? StatoLampadina.ACCESSO : StatoLampadina.SPENTO;
        aggiornaStatoLampada();
    }

    public void cambiaSecondoInterruttore() {
        this.secondoInterruttore = (this.secondoInterruttore == StatoLampadina.SPENTO) ? StatoLampadina.ACCESSO : StatoLampadina.SPENTO;
        aggiornaStatoLampada();
    }

    private void aggiornaStatoLampada() {
        if (this.primoInterruttore == StatoLampadina.ACCESSO || this.secondoInterruttore == StatoLampadina.ACCESSO) {
            this.lampadina = StatoLampadina.ACCESSO;
        } else {
            this.lampadina = StatoLampadina.SPENTO;
        }
    }


    @Override
    public String toString() {
        return "Circuito { " +
                "primoInterruttore=" + primoInterruttore +
                ", secondoInterruttore=" + secondoInterruttore +
                ", lampada=" + lampadina +
                " }";
    }
}
