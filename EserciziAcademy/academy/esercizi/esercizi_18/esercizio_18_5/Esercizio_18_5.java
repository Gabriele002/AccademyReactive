package academy.esercizi.esercizi_18.esercizio_18_5;

import java.math.BigDecimal;

public class Esercizio_18_5 {
    public static void main(String[] args) {
        Esercizio_18_5 esercizio_18_5 = new Esercizio_18_5();
        esercizio_18_5.soluzione();
    }


    public void soluzione(){
        final BigDecimal interessi = new BigDecimal("0.69");
        BigDecimal saldo = new BigDecimal(1824);
        BigDecimal interessiTot = (saldo.multiply(interessi)).divide(new BigDecimal(100)) ;

        BigDecimal saldoFinale = saldo.add(interessiTot);

        System.out.print(saldoFinale);

    }
}
