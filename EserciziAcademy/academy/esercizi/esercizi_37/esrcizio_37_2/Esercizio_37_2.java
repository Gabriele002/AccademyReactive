package academy.esercizi.esercizi_37.esrcizio_37_2;

public class Esercizio_37_2 {
    public static void main(String[] args) {
        Esercizio_37_2 esercizio_37_2 = new Esercizio_37_2();
        esercizio_37_2.soluzione();

    }

    private void soluzione() {
//        int valore = 2;
//        int potenza = 5;
//        System.out.println(calcolaPotenza(valore, potenza));
        String pippo= "pippo";
        int num = 5;

        System.out.println(concatenaString(pippo, num));

    }

    public static int calcolaPotenza(int valore, int n) {
        if (n == 0) {
            return 1;
        } else {
            return valore * calcolaPotenza(valore, n - 1);
        }
    }


    public static String concatenaString(String string, int num){
        if (num == 0){
            return "";
        } else {
            System.out.println("Sono entrato nel metodo numero " + num);
            String a = string + concatenaString(string, num - 1);
            System.out.println("Sto uscendo dal metodo numero " + num);
            return a;
        }

    }
}
