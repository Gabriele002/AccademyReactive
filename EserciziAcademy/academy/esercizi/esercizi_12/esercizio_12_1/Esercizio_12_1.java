package academy.esercizi.esercizi_12.esercizio_12_1;


public class Esercizio_12_1 {
    public static void main(String[] args) {
        Esercizio_12_1 esercizio_12_1 = new Esercizio_12_1();
        esercizio_12_1.soluzione();

    }

    private void soluzione() {
        String[][] pratoPopolato = Prato.popolaPrato();
        Prato.stampaPrato(pratoPopolato);
    }

}
