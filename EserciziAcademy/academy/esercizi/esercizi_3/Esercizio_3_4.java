package academy.esercizi.esercizi_3;

public class Esercizio_3_4 {
    public static void main(String[] args) {
        Esercizio_3_4 esercizio_3_4 = new Esercizio_3_4();
        esercizio_3_4.soluzione();
    }

    public void soluzione() {
        System.out.println("Popolazione per continente (in milioni)");
        String[] stati = {"Africa", "Asia", "Australia", "Europa", "Nord America", "Sud America"};
        int[][] popolazione = {
                {106, 107, 111, 133, 221, 767, 1766},
                {502, 107, 111, 133, 221, 767, 1766},
                {2, 107, 111, 133, 221, 767, 1766},
                {163, 107, 111, 133, 221, 767, 1766},
                {2, 107, 111, 133, 221, 767, 1766},
                {16, 107, 111, 133, 221, 767, 1},
        };

        int[] anni = {1750, 1800, 1850, 1900, 1950, 2000, 2050};

        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s", "Anno");
        for (int anno : anni) {
            System.out.printf("%-15s", anno);
        }

        System.out.println(" ");

        int[] totaliPerAnno = new int[anni.length];

        for (int i = 0; i < stati.length; i++) {
            System.out.printf("%-15s", stati[i]);
            for (int j = 0; j < popolazione[i].length; j++) {
                System.out.printf("%-15s", popolazione[i][j]);
                totaliPerAnno[j] += popolazione[i][j];
            }
            System.out.println(" ");
        }
        System.out.println("---------------------------------------------------------------");

        System.out.printf("%-15s", "Pop tot");
        for (int totaleAnno : totaliPerAnno) {
            System.out.printf("%-15s", totaleAnno);
        }
        System.out.println();
    }

}
