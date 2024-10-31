package academy.esercizi.esercizi_27.esercizio_27_2;

public class Esercizio_27_2 {

    public static void main(String[] args) {
        Esercizio_27_2 esercizio_27_2 = new Esercizio_27_2();
        esercizio_27_2.soluzione();
    }

    public void soluzione() {
        Poker gioco = new Poker();
        gioco.iniziaGioco();
        gioco.valutaMano();
    }
}

