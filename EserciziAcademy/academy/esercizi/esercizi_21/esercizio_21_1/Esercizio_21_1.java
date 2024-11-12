package academy.esercizi.esercizi_21.esercizio_21_1;
import java.util.Scanner;

public class Esercizio_21_1 {
    public static void main(String[] args) {
        Esercizio_21_1 esercizio_21_1 = new Esercizio_21_1();
        esercizio_21_1.soluzione();
    }

    private void soluzione(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Inseriscimi un numero di almeno 3 cifre");
        StringBuilder stringBuilder = new StringBuilder(scn.next());
        if (stringBuilder.length() <= 2){
            stringBuilder.append(0);
        }
        int treCaratteri = Integer.parseInt(stringBuilder.substring(stringBuilder.length() - 3));
        int stringaCapovolta = Integer.parseInt(reverseString(String.valueOf(treCaratteri)));
        System.out.println("stringBuilder = " + stringBuilder);
        int risultatoPrimaOperazione = treCaratteri -stringaCapovolta;
        System.out.println("risultatoPrimaOperazione = " + risultatoPrimaOperazione);
        int risultatoprimaOperazioneInvertita = Integer.parseInt(reverseString(String.valueOf(risultatoPrimaOperazione)));
        System.out.println("risultatoprimaOperazioneInvertita = " + risultatoprimaOperazioneInvertita);
        int sommaDifferenzaEInvertita = risultatoPrimaOperazione + risultatoprimaOperazioneInvertita;
        System.out.println("sommaDifferenzaEInvertita = " + sommaDifferenzaEInvertita);
    }


    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
