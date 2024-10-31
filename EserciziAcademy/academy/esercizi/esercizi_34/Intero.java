package academy.esercizi.esercizi_34;

public class Intero extends OggettoMatematico implements Raddoppiabile, Triplicabile {

    private int v = (int)super.v;
    public Intero(int v) {
        this.v = v;
    }

    @Override
    public double getValore() {
        return v;
    }

    @Override
    public void raddoppia() {
        v *= 2;
    }

    @Override
    public void triplica() {
        v *= 3;
    }

    @Override
    public String stampa() {
        return String.valueOf(v);
    }
}



