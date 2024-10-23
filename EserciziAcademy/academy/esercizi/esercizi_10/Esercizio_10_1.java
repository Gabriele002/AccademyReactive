package academy.esercizi.esercizi_10;

public class Esercizio_10_1 {
    public static void main(String[] args) {
        Esercizio_10_1 esercizio_10_1 = new Esercizio_10_1();
        esercizio_10_1.soluzione();
    }

    private void soluzione (){
        System.out.println(convertitore(3450));
    }

    private  static String convertitore(int numeroDaConvertire){
        if (numeroDaConvertire < 1 || numeroDaConvertire > 3999) {
            return "Numero non valido. Deve essere tra 1 e 3999.";
        }else {
            String[] romani = { "M","D","C","L","X","V","I"};
            int[] numeriInteri = {1000,500,100,50,10,5,1};
            StringBuilder numeroConvertito = new StringBuilder();
            for (int i = 0; i < numeriInteri.length; i++) {
                while (numeroDaConvertire >= numeriInteri[i]){
                    numeroConvertito.append(romani[i]);
                    numeroDaConvertire -= numeriInteri[i];
                }
            }
            return numeroConvertito.toString();
        }
    }
}
