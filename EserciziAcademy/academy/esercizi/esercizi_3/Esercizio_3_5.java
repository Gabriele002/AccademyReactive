package academy.esercizi.esercizi_3;


public class Esercizio_3_5 {
    public static void main(String[] args) {
        Esercizio_3_5 esercizio_3_5 = new Esercizio_3_5();
        esercizio_3_5.soluzione();

    }

    public void soluzione(){


        String [][] b = new String[3][3];

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j <= i; j++) {
                b[i][j] = "b[" + i +"]" +"[" + j + "]";
            }
        }

        for (String[] strings : b) {
            for (String string : strings) {
                if (string != null) {
                    System.out.print(string);
                }
            }
            System.out.println(" ");
        }
    }
}
