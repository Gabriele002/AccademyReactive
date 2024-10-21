package academy.esercizi;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Eserizio_1_2 {

    public static void main(String[] args) {
        calcolaInteressi();
    }

    private static void calcolaInteressi() {
        BigDecimal saldoIniziale = new BigDecimal("1000.00");
        BigDecimal tassoInteresse = new BigDecimal("0.05");

        BigDecimal saldoFinale = saldoIniziale;

        for (int i = 1; i <= 3; i++) {
            BigDecimal interessiAnno = saldoFinale.multiply(tassoInteresse).setScale(2, RoundingMode.HALF_UP);
            saldoFinale = saldoFinale.add(interessiAnno);
            System.out.printf("Anno %d: Interessi guadagnati: %.2f euro, Saldo finale: %.2f euro %n",
                    i, interessiAnno, saldoFinale);
        }
    }

}

