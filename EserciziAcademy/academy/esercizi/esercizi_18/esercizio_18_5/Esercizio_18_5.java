package academy.esercizi.esercizi_18.esercizio_18_5;

public class Esercizio_18_5 {
    public static void main(String[] args) {
        Esercizio_18_5 esercizio_18_5 = new Esercizio_18_5();
        esercizio_18_5.soluzione();
    }


    public void soluzione(){
        final double interessi = 0.69;
        double saldo = 1824;
        double interessiTot = saldo * interessi / 100;

        double saldoFinale = saldo + interessiTot;

        System.out.printf("Saldo finale: %.4f ", saldoFinale);

    }
}
