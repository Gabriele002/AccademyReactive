package academy.esercizi.esercizio_28;

public class Esercizio_28 {
    public static void main(String[] args) {
        Esercizio_28 esercizio_28 = new Esercizio_28();
        esercizio_28.soluzione();
    }

    public void soluzione(){
        String[][] tabella = {
                {".", ".", ".",".", ".", ".", "."},
                {".", ".", ".",".", ".", ".", "."},
                {".", ".", ".",".", ".", ".", "."},
                {".", ".", ".",".", ".", ".", "."},
                {".", ".", ".",".", ".", ".", "."},
                {".", ".", ".",".", ".", ".", "."},
                {".", ".", ".",".", ".", ".","."}
        };

        stampaTabella(tabella);
    }



    public static void stampaTabella(String[][] tabella) {
        System.out.println("-------------------------");
        for (int i = 0; i < tabella.length; i++) {
            System.out.println(" " +tabella[i][0] + " | " + tabella[i][1] + " | " + tabella[i][2] + );
            if (i < tabella.length - 1) {
                System.out.println("---|---|---");
            }
        }
        System.out.println("-------------------------");
    }
}
