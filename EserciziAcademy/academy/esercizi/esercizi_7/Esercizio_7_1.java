package academy.esercizi.esercizi_7;

import java.math.BigDecimal;

public class Esercizio_7_1 {
    public static void main(String[] args) {
        Esercizio_7_1 esercizio_7_1 = new Esercizio_7_1();
        esercizio_7_1.soluzione();
    }

    public void soluzione(){

        BigDecimal prezzo = BigDecimal.valueOf(130);
        BigDecimal sconto = BigDecimal.ZERO;
        BigDecimal prezzoScontato = BigDecimal.ZERO;

        if (prezzo.compareTo(BigDecimal.valueOf(128)) < 0){
            sconto = prezzo.multiply(BigDecimal.valueOf(0.08) );
            prezzoScontato = prezzo.subtract(sconto);
        } else {
            sconto = prezzo.multiply(BigDecimal.valueOf(0.16) );
            prezzoScontato = prezzo.subtract(sconto);
        }

        System.out.printf("%-4s %-4s %-4s %n", "Prezzo", "Sconto", "Totale");
        System.out.printf("%-6.2f %-6.2f %-6.2f", prezzo, sconto, prezzoScontato);



    }
}
