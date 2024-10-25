package academy.esercizi.esercizi_7;


public class Esercizio_7_2 {
    public static void main(String[] args) {
        Esercizio_7_2 esercizio_7_2 = new Esercizio_7_2();
        esercizio_7_2.soluzione();
    }

    public void soluzione() {

        int scoreA = 0;
        int scoreB =0;
        if (scoreA > scoreB) {
            System.out.println("Il giocatore A ha vinto");

        } else if (scoreB > scoreA) {
            System.out.println("Il giocatore B ha vinto");
        } else {
            System.out.println("Pareggio");
        }
    }

}
