package academy.esercizi.esercizi_15.esercizio_15_1;

public class Esercizio_15_1 {
    public static void main(String[] args) {
        Esercizio_15_1 esercizio_15_1 = new Esercizio_15_1();
        esercizio_15_1.soluzione();
    }

    private void soluzione() {
        TimeDeposit timeDeposit = new TimeDeposit();
        System.out.println(timeDeposit);
        timeDeposit.accreditaInteressi();
        System.out.println(timeDeposit);
        timeDeposit.prelevaTutto();

    }



}
