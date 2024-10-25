package academy.esercizi.esercizi_5;

public class Esercizio_5_2 {

    public static void main(String[] args) {
        Esercizio_5_2 esercizio_5_2 = new Esercizio_5_2();
        esercizio_5_2.soluzione();
    }

    public void soluzione(){
        String provaMetodoContaSpazzi = "Ciao sono Gariele";
        System.out.println("Gli spazzi presenti in questa stringa sono(con il while): " +rimuoviTrattiniConWhile(provaMetodoContaSpazzi));
        System.out.println("Gli spazzi presenti in questa stringa sono(con il for): " +rimuoviTrattini(provaMetodoContaSpazzi));

    }

    public static int rimuoviTrattini(String str) {
        int spazziInUnaStringa = 0;
        for (int i = 0; i < str.length(); i++) {
            char carattere = str.charAt(i);
            if (carattere == ' ') {
               spazziInUnaStringa ++;
            }
        }
        return spazziInUnaStringa;
    }

    public static int rimuoviTrattiniConWhile(String str) {
        int contatore = 0;
        int spazziInUnaStringa = 0;

        while (contatore < str.length()){
            char carattere = str.charAt(contatore);
            if (carattere == ' ') {
                spazziInUnaStringa ++;
            }
            contatore++;
        }
        return spazziInUnaStringa;
    }
}

