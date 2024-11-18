package academy.esercizi.esercizi_37.esercizio_37_1;

public class Esercizio_37_1 {
    public static void main(String[] args) {
        Esercizio_37_1 esercizio_37_1 = new Esercizio_37_1();
        esercizio_37_1.soluzione();
    }

    final int NUMERO_ASTERISCHI = 5;

    private void soluzione() {
        stampaAsterischi(NUMERO_ASTERISCHI);
    }

    public static void stampaAsterischi(int n) {
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();
        stampaAsterischi(n - 1);
    }
}
