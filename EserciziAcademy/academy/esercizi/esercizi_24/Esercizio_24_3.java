package academy.esercizi.esercizi_24;


public class Esercizio_24_3 {
    public static void main(String[] args) {
        Esercizio_24_3 esercizio_24_3 = new Esercizio_24_3();
        esercizio_24_3.soluzione();
    }

    private void soluzione() {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {1, 2, 4, 5, 3};
        int[] array3 = {5, 2, 4, 5, 3};

        if (controllaPermutazione(array1,array2)){
            System.out.println("Gli array sono permutabili");
        }else {
            System.out.println("Gli array non sono permutabili");
        }

        if (controllaPermutazione(array1,array3)){
            System.out.println("Gli array sono permutabili");
        }else {
            System.out.println("Gli array non sono permutabili");
        }

    }

    public boolean controllaPermutazione(int[] array1, int[] array2) {
        boolean[] arrayControllo = new boolean[array1.length];
        int contatore = 0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (!arrayControllo[j] && contatore == i) {
                    if (array1[i] == array2[j]) {
                        contatore++;
                        arrayControllo[j] = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


