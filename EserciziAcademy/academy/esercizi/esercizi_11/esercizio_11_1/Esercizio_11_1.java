package academy.esercizi.esercizi_11.esercizio_11_1;


public class Esercizio_11_1 {
    public static void main(String[] args) {
        Esercizio_11_1 esercizio_11_1 = new Esercizio_11_1();
        esercizio_11_1.soluzione();
    }

    private void soluzione() {
        stampaTabella(5, 5);
    }


    public void stampaTabella(int numeroRighe, int numeroColonne) {


        for (int i = 0; i < numeroRighe; i++) {
            for (int j = 0; j < numeroColonne; j++) {
                if (i == 0 || i == numeroRighe - 1 || j == 0 || j == numeroColonne - 1) {
                    System.out.printf("%-3s", "■");
                } else if ((i == numeroRighe / 2 || i == (numeroRighe - 1) / 2) &&( j == numeroColonne/2 || j == (numeroColonne -1)/2)){
                    System.out.printf("%-3s", "■");
                } else {
                    System.out.printf("%-3s", " ");
                }
            }
            System.out.println();
        }

    }


}
