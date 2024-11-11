package academy.esercizi.esercizi_18.esercizio_18_3;

public class Esercizio_18_3 {
    public static void main(String[] args) {
        Esercizio_18_3 esercizio_18_3 = new Esercizio_18_3();
        esercizio_18_3.soluzione();
    }

    private void soluzione() {
        RoachPopulation roachPopulation = new RoachPopulation(10,3);
        roachPopulation.simula(10);
    }
}
