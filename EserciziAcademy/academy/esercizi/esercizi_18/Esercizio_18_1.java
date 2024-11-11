package academy.esercizi.esercizi_18;


public class Esercizio_18_1 {
    public static void main(String[] args) {
        Esercizio_18_1 esercizio_18_1 = new Esercizio_18_1();
        esercizio_18_1.soluzione();
    }

    private void soluzione() {
        final int x = 8;
        final int y = 8;

        String[][] schacchiera = new String[x][y];

        for (int i = 0; i < schacchiera.length; i++) {
            for (int j = 0; j < schacchiera[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    schacchiera[i][j] = "0";
                } else {
                    schacchiera[i][j] = "1";
                }
            }
        }


        for (int i = 0; i < schacchiera.length; i++) {
            for (int j = 0; j < schacchiera[i].length; j++) {
                System.out.print(schacchiera[i][j] + " ");
            }
            System.out.println();
        }
    }
}
