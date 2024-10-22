package academy.esercizi.esercizi_9;

import java.math.BigDecimal;
import java.util.Scanner;

public class Esercizio_9_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nazionalita;
        BigDecimal ral;
        while (true) {
            System.out.print("Inserisci la nazionalità (Italiana o Americana): ");
            nazionalita = scanner.nextLine();
            if (nazionalita.equalsIgnoreCase("Italiana") || nazionalita.equalsIgnoreCase("Americana")) {
                System.out.print("Inserisci il tuo RAL (deve essere positivo): ");
                ral = scanner.nextBigDecimal();
                if (ral.compareTo(BigDecimal.ZERO) > 0) {
                    break;
                } else {
                    System.out.println("Il RAL deve essere un valore positivo. Riprova.");
                }
            } else {
                System.out.println("Nazionalità non valida. Riprova.");
                System.out.println();
            }
        }


        BigDecimal tasse = calcolaTasse(nazionalita, ral);
        BigDecimal netto = ral.subtract(tasse);
        stampaTabella(ral, tasse, netto);
    }

    public static BigDecimal calcolaTasse(String nazionalita, BigDecimal ral) {
        BigDecimal tasse = BigDecimal.ZERO;
        if (nazionalita.equalsIgnoreCase("Italiana")) {
            tasse = calcolaTasseItaliana(ral);
        } else if (nazionalita.equalsIgnoreCase("Americana")) {
            tasse = calcolaTasseAmericana(ral);
        }
        return tasse;
    }


    private static BigDecimal calcolaTasseItaliana(BigDecimal ral) {
        final BigDecimal soglia15000 = BigDecimal.valueOf(15000);
        final BigDecimal soglia28000 = BigDecimal.valueOf(28000);
        final BigDecimal soglia50000 = BigDecimal.valueOf(50000);

        final BigDecimal aliquota23 = BigDecimal.valueOf(0.23);
        final BigDecimal aliquota25 = BigDecimal.valueOf(0.25);
        final BigDecimal aliquota35 = BigDecimal.valueOf(0.35);
        final BigDecimal aliquota43 = BigDecimal.valueOf(0.43);

        if (ral.compareTo(soglia15000) <= 0) {
            return ral.multiply(aliquota23);
        } else if (ral.compareTo(soglia28000) <= 0) {
            return soglia15000.multiply(aliquota23)
                    .add(ral.subtract(soglia15000).multiply(aliquota25));
        } else if (ral.compareTo(soglia50000) <= 0) {
            return soglia15000.multiply(aliquota23)
                    .add(soglia28000.subtract(soglia15000).multiply(aliquota25))
                    .add(ral.subtract(soglia28000).multiply(aliquota35));
        } else {
            return soglia15000.multiply(aliquota23)
                    .add(soglia28000.subtract(soglia15000).multiply(aliquota25))
                    .add(soglia50000.subtract(soglia28000).multiply(aliquota35))
                    .add(ral.subtract(soglia50000).multiply(aliquota43));
        }
    }

    private static BigDecimal calcolaTasseAmericana(BigDecimal ral) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sei coniugato? (sì/no): ");
        boolean statoCivile = scanner.next().equalsIgnoreCase("si");
        System.out.println(statoCivile);
        if (statoCivile) {
            return calcolaTasseConiugati(ral);
        } else {
            return calcolaTasseNonConiugati(ral);
        }
    }

    private static BigDecimal calcolaTasseConiugati(BigDecimal ral) {
        final BigDecimal soglia16000 = BigDecimal.valueOf(16000);
        final BigDecimal soglia64000 = BigDecimal.valueOf(64000);

        final BigDecimal aliquota10 = BigDecimal.valueOf(0.10);
        final BigDecimal aliquota15 = BigDecimal.valueOf(0.15);
        final BigDecimal aliquota25 = BigDecimal.valueOf(0.25);

        if (ral.compareTo(soglia16000) <= 0) {
            return ral.multiply(aliquota10);
        } else if (ral.compareTo(soglia64000) <= 0) {
            return soglia16000.multiply(aliquota10)
                    .add(ral.subtract(soglia16000).multiply(aliquota15));
        } else {
            return soglia16000.multiply(aliquota10)
                    .add(soglia64000.subtract(soglia16000).multiply(aliquota15))
                    .add(ral.subtract(soglia64000).multiply(aliquota25));
        }
    }

    private static BigDecimal calcolaTasseNonConiugati(BigDecimal ral) {
        final BigDecimal soglia8000 = BigDecimal.valueOf(8000);
        final BigDecimal soglia32000 = BigDecimal.valueOf(32000);

        final BigDecimal aliquota10 = BigDecimal.valueOf(0.10);
        final BigDecimal aliquota15 = BigDecimal.valueOf(0.15);
        final BigDecimal aliquota25 = BigDecimal.valueOf(0.25);

        if (ral.compareTo(soglia8000) <= 0) {
            return ral.multiply(aliquota10);
        } else if (ral.compareTo(soglia32000) <= 0) {
            return soglia8000.multiply(aliquota10)
                    .add(ral.subtract(soglia8000).multiply(aliquota15));
        } else {
            return soglia8000.multiply(aliquota10)
                    .add(soglia32000.subtract(soglia8000).multiply(aliquota15))
                    .add(ral.subtract(soglia32000).multiply(aliquota25));
        }
    }

    private static void stampaTabella(BigDecimal ral, BigDecimal tasse, BigDecimal netto) {
        System.out.println("-----------------------------------------");
        System.out.printf("%-10s %-10s %-10s%n", "RAL", "Tasse", "Netto");
        System.out.println("-----------------------------------------");
        System.out.printf("%-10s %-10s %-10s%n", ral, tasse, netto);
        System.out.println("-----------------------------------------");
    }
}