package academy.esercizi.esercizi_23.esercizio_23_1;

public class Esercizio_23_1 {
    public static void main(String[] args) {
        Esercizio_23_1 esercizio_23_1 = new Esercizio_23_1();
        esercizio_23_1.soluzione();
    }

    private void soluzione() {
        Clienti gabriele = new Clienti("Gabriele");
        gabriele.setNumeroDiFilmNoleggiati(12);
        gabriele.setNumeroDiPersoneInvitate(123);
        gabriele.calcolaSconto();
        System.out.println(gabriele);
    }
}
