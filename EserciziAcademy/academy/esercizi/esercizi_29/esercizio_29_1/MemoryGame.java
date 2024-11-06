package academy.esercizi.esercizi_29.esercizio_29_1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MemoryGame {
    private Tile[][] tabella;
    private Giocatore giocatore1 = new Giocatore("Gabriele");
    private Giocatore giocatore2 = new Giocatore("Pippo");
    private static final int totalePaio = (Grid.righe * Grid.colonne) / 2;

    public void avviaPartita() {
        int turnoAttuale = 1;
        int coppieTrovate = 0;
        Scanner scn;

        boolean modalitaTest = true;

        if (modalitaTest) {
            tabella = Grid.popolaTabellaConValoriFissi();
            try {
                scn = new Scanner(new File("C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\provaMemory.text"));
            } catch (FileNotFoundException e) {
                System.out.println("Errore: File non trovato.");
                return;
            }
        } else {
            tabella = Grid.tabellaCompleta();
            scn = new Scanner(System.in);
        }
        while (coppieTrovate < totalePaio) {
            Giocatore giocatoreAttuale = (turnoAttuale == 1) ? giocatore1 : giocatore2;
            System.out.println(giocatoreAttuale.getNome() + ", è il tuo turno!");

            Grid.stampaTabella(tabella);

            int primaCartaRiga;
            int primaCartaColonna;

            do {
                System.out.println("Scegli la riga della prima carta: ");
                primaCartaRiga = scn.nextInt() - 1;
                System.out.println("Scegli la colonna della prima carta: ");
                primaCartaColonna = scn.nextInt() - 1;
            } while (isCartaValida(primaCartaRiga, primaCartaColonna));

            int secondaCartaRiga;
            int secondaCartaColonna;
            do {
                System.out.println("Scegli la riga della seconda carta: ");
                secondaCartaRiga = scn.nextInt() - 1;
                System.out.println("Scegli la colonna della seconda carta: ");
                secondaCartaColonna = scn.nextInt() - 1;
            } while (isCartaValida(secondaCartaRiga, secondaCartaColonna));


            tabella[primaCartaRiga][primaCartaColonna].scopri();
            tabella[secondaCartaRiga][secondaCartaColonna].scopri();

            Grid.stampaTabella(tabella);

            if (tabella[primaCartaRiga][primaCartaColonna].getValore() == tabella[secondaCartaRiga][secondaCartaColonna].getValore()) {
                System.out.println("Hai trovato una coppia!");
                giocatoreAttuale.aumentoPunteggio();
                coppieTrovate++;
                if ((primaCartaRiga == secondaCartaRiga - 1 || primaCartaRiga == secondaCartaRiga + 1) && (primaCartaColonna == secondaCartaColonna)) {
                    giocatoreAttuale.aumentoPunteggio();
                } else if ((primaCartaColonna == secondaCartaColonna - 1 || primaCartaColonna == secondaCartaColonna + 1) && (primaCartaRiga == secondaCartaRiga)) {
                    giocatoreAttuale.aumentoPunteggio();
                }
            } else {
                System.out.println("Non è una coppia, riprova.");
                tabella[primaCartaRiga][primaCartaColonna].nascondi();
                tabella[secondaCartaRiga][secondaCartaColonna].nascondi();
            }
            turnoAttuale = (turnoAttuale == 1) ? 2 : 1;
        }

        System.out.println("Gioco finito!");
        System.out.println(giocatore1);
        System.out.println(giocatore2);
        if (giocatore1.getPunteggio() < giocatore2.getPunteggio()) {
            System.out.println("Il vincitore e' : " + giocatore2.getNome() + " con un totale di: " + giocatore2.getPunteggio());
        } else if (giocatore1.getPunteggio() > giocatore2.getPunteggio()){
            System.out.println("Il vincitore e' : " + giocatore1.getNome() + " con un totale di: " + giocatore1.getPunteggio());
        } else {
            System.out.println("La partita e finita in pareggio");
        }


    }

    private boolean isCartaValida(int riga, int colonna) {
        return riga < 0 || riga >= Grid.righe || colonna < 0 || colonna >= Grid.colonne;
    }
}




