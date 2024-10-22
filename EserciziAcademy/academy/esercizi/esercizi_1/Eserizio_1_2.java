package academy.esercizi.esercizi_1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Eserizio_1_2 {

    public static void main(String[] args) {
        calcolaInteressi();
    }

    public static void calcolaInteressi() {
        Scanner scn = new Scanner(System.in);

        System.out.print("Inserisci il numero di anni: ");
        int anni = scn.nextInt();

        System.out.print("Inserisci il tasso di interesse: ");
        BigDecimal tassoInteressePercentuale = scn.nextBigDecimal();
        BigDecimal tassoInteresse = tassoInteressePercentuale.divide(BigDecimal.valueOf(100));


        System.out.print("Inserisci il saldo iniziale: ");
        BigDecimal saldoIniziale = scn.nextBigDecimal();

        BigDecimal saldoFinale = saldoIniziale;

        for (int i = 1; i <= anni; i++) {
            BigDecimal interessiAnno = saldoFinale.multiply(tassoInteresse).setScale(2, RoundingMode.HALF_UP);
            saldoFinale = saldoFinale.add(interessiAnno);
            System.out.printf("Anno %d: Interessi guadagnati: %.2f euro, Saldo finale: %.2f euro %n",
                    i, interessiAnno, saldoFinale);
        }
        scn.close();
    }

}

