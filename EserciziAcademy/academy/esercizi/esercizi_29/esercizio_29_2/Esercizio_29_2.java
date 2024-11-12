package academy.esercizi.esercizi_29.esercizio_29_2;

public class Esercizio_29_2 {
    public static void main(String[] args) {
        Esercizio_29_2 esercizio_29_2 = new Esercizio_29_2();
        esercizio_29_2.soluzione();
    }


    private void soluzione() {
        Cliente pippo = new Cliente("Pippo");
        pippo.acquista(100, 1);
        pippo.acquista(100, 2);
        pippo.acquista(100, 3);
    }
}
