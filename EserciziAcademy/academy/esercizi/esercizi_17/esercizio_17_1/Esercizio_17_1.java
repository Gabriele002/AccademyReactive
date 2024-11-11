package academy.esercizi.esercizi_17.esercizio_17_1;

public class Esercizio_17_1 {
    public static void main(String[] args) {
        Esercizio_17_1 esercizio_17_1 = new Esercizio_17_1();
        esercizio_17_1.soluzione();
    }

    private void soluzione() {
        Bug bug = new Bug(2);
        bug.move();
        bug.move();
        bug.stampaPercorso();
        bug.gira();
        bug.move();
        bug.move();
        bug.stampaPercorso();
    }
}
