package academy.esercizi.esercizi_37.esercizio_37_3;

public class Esercizio_37_3 {
    public static void main(String[] args) {
        Esercizio_37_3 esercizio_37_3 = new Esercizio_37_3();
        esercizio_37_3.soluzione();
    }


    private void soluzione() {
        int numero1 = 24;
        int numero2 = 36;

        System.out.println(calcolaMCD(24, 36));
    }

    private int calcolaMCD(int numero1, int numero2){
        if (numero2 == 0){
            return numero1;
        }
        return calcolaMCD(numero2, numero1 %numero2);
    }
}
