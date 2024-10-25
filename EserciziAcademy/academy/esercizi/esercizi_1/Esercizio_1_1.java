package academy.esercizi.esercizi_1;
public class Esercizio_1_1 {
    public static void main(String[] args) {
       Esercizio_1_1 esercizio_1_1 = new Esercizio_1_1();
        System.out.println(esercizio_1_1.soluzione(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

    }

    private int soluzione(int...interi) {
        int somma = 0;
        for (int i = 0; i < interi.length; i++) {
            somma += interi[i];
        }
        return somma;
    }
}
