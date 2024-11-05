package academy.esercizi.esercizi_29.esercizio_29_1;

public class Tile {

    private int valore;
    private boolean isScoperta;

    public Tile(int valore) {
        this.valore = valore;
        this.isScoperta = false;
    }


    @Override
    public String toString() {
        return "valore=" + valore;
    }
}
