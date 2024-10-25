package academy.esercizi.esercizi_2;

public class Esercizio_2_4 {
    public static void main(String[] args) {
        Esercizio_2_4 esercizio_2_4 = new Esercizio_2_4();
        esercizio_2_4.soluzione();
    }

    public void soluzione(){
        int[]arrayDiInteriDiCinqueElementi  = {1,2,3,4,5};
        int[] arrayDiInteriDiDieciElementi = new int[10];
        for (int i = 0; i < arrayDiInteriDiCinqueElementi.length; i++) {
            arrayDiInteriDiDieciElementi[i] = arrayDiInteriDiCinqueElementi[i];
        }
        for (int elementi : arrayDiInteriDiDieciElementi) {
            System.out.println(elementi);
        }
    }
}
