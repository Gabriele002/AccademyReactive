package academy.esercizi.esercizi_19.esercizio_19_1;

public enum Maniglia {
    CHIUSA(0), APERTA(1);

    private final int valore;

    Maniglia(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return this.valore;
    }
}
