package academy.esercizi.esercizi_5;



public class Esercizio_5_5 {
    public static void main(String[] args) {
        Esercizio_5_5 esercizio_5_5 = new Esercizio_5_5();
        esercizio_5_5.soluzione();
    }

    public void soluzione() {

        int[] sequenza = {1, 2, 3, 4,4, 5, 6, 7, 8};
        for (int i = 0; i < sequenza.length - 1; i++) {
            if (sequenza[i] == sequenza[i + 1]) {
                System.out.println("Ci sono duplicati vicini");
            }
        }
    }
}
