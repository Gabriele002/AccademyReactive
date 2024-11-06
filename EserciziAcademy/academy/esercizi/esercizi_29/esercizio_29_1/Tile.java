package academy.esercizi.esercizi_29.esercizio_29_1;

public class Tile {

    private int valore;
    private boolean isScoperta;

    public Tile(int valore) {
        this.valore = valore;
        this.isScoperta = false;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    public boolean isScoperta() {
        return isScoperta;
    }

    public void setScoperta(boolean scoperta) {
        isScoperta = scoperta;
    }

    public void scopri() {
        this.isScoperta = true;
    }

    public void nascondi() {
        this.isScoperta = false;
    }

    @Override
    public String toString() {
        if (isScoperta) {
            return String.valueOf(valore);
        } else {
            return "O";
        }
    }
}
