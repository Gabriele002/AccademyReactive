package academy.esercizi.esercizi_34;

public class Frazione extends OggettoMatematico implements Raddoppiabile, Triplicabile {
    private int numeratore;
    private int denominatore;

    public Frazione(int numeratore, int denominatore) {
        this.numeratore = numeratore;
        this.denominatore = denominatore;
    }

    public boolean isFrazioenPropria() {
        return numeratore % denominatore == 0;
    }

    public Frazione inversa() {
        return new Frazione(denominatore,numeratore);
    }

    public int getNumeratore() {
        return numeratore;
    }

    public int getDenominatore() {
        return denominatore;
    }

    public String stampa() {
        return "" + (numeratore / denominatore);
    }

    public double getValore() {
        return (double) numeratore / denominatore;
    }

    @Override
    public void raddoppia() {
        super.v *= 2;
    }

    @Override
    public void triplica() {
        super.v *= 3;
    }

    @Override
    public boolean isDimezzabile() {
        return getDenominatore() % 2 == 0;
    }
}
