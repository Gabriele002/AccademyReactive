package academy.esercizi.esercizi10;

import com.sun.org.apache.xpath.internal.operations.Neg;

public class Esercizio_10_1 {
    public static void main(String[] args) {
        Esercizio_10_1 esercizio_10_1 = new Esercizio_10_1();
        esercizio_10_1.soluzione();
    }

    private void soluzione (){
        System.out.println(convertitore(1200));
    }

    private  static String convertitore(int numeroDaConvertire){
        String[] romani = { "I","V","X","L","C","D","M"};
        int[] valori = {1,5,10,50,100,500,1000};
        StringBuilder risultato = new StringBuilder();
        for (int i = 0; i < valori.length; i++) {
            while (numeroDaConvertire >= valori[i]){
                risultato.append(romani[i]);
            }
        }
        return risultato.toString();
    }




    }
