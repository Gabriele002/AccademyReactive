package academy.esercizi;

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
        if (tasse != null) {
            BigDecimal netto = ral.subtract(tasse);
            stampaTabella(ral, tasse, netto);
        }

    }

    public static BigDecimal calcolaTasse(String nazionalita, BigDecimal ral) {
        BigDecimal tasse = BigDecimal.ZERO;
            //Nazionalita italiana
        if (nazionalita.equalsIgnoreCase("Italiana")) {
            final BigDecimal soglia15000 = BigDecimal.valueOf(15000);
            final BigDecimal soglia28000 = BigDecimal.valueOf(28000);
            final BigDecimal soglia50000 = BigDecimal.valueOf(50000);

            final BigDecimal aliquota23 = BigDecimal.valueOf(0.23);
            final BigDecimal aliquota25 = BigDecimal.valueOf(0.25);
            final BigDecimal aliquota35 = BigDecimal.valueOf(0.35);
            final BigDecimal aliquota43 = BigDecimal.valueOf(0.43);

            if (ral.compareTo(soglia15000) <= 0) {
                tasse = ral.multiply(aliquota23);
            } else if (ral.compareTo(soglia28000) <= 0) {
                BigDecimal tasseFino15000 = soglia15000.multiply(aliquota23);
                BigDecimal tasseSopra15000 = ral.subtract(soglia15000).multiply(aliquota25);
                tasse = tasseFino15000.add(tasseSopra15000);
            } else if (ral.compareTo(soglia50000) <= 0) {
                BigDecimal tasseFino15000 = soglia15000.multiply(aliquota23);
                BigDecimal tasseFino28000 = soglia28000.subtract(soglia15000).multiply(aliquota25);
                BigDecimal tasseSopra28000 = ral.subtract(soglia28000).multiply(aliquota35);
                tasse = tasseFino15000.add(tasseFino28000).add(tasseSopra28000);
            } else {
                BigDecimal tasseFino15000 = soglia15000.multiply(aliquota23);
                BigDecimal tasseFino28000 = soglia28000.subtract(soglia15000).multiply(aliquota25);
                BigDecimal tasseFino50000 = soglia50000.subtract(soglia28000).multiply(aliquota35);
                BigDecimal tasseSopra50000 = ral.subtract(soglia50000).multiply(aliquota43);
                tasse = tasseFino15000.add(tasseFino28000).add(tasseFino50000).add(tasseSopra50000);
            }
            // Nazionalita Americana
        }else if (nazionalita.equalsIgnoreCase("Americana")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Sei coniugato? (sì/no): ");
            String statoCivile = scanner.next().toLowerCase();
            if (statoCivile.equalsIgnoreCase("si")) {
                // Tassazione per coniugati
               final BigDecimal soglia16000 = BigDecimal.valueOf(16000);
               final BigDecimal soglia64000 = BigDecimal.valueOf(64000);

               final BigDecimal aliquota10 = BigDecimal.valueOf(0.10);
               final BigDecimal aliquota15 = BigDecimal.valueOf(0.15);
               final BigDecimal aliquota25 = BigDecimal.valueOf(0.25);

                if (ral.compareTo(soglia16000) <= 0) {
                    tasse = ral.multiply(aliquota10);
                } else if (ral.compareTo(soglia64000) <= 0) {
                    BigDecimal tasseFino16000 = soglia16000.multiply(aliquota10);
                    BigDecimal tasseSopra16000 = ral.subtract(soglia16000).multiply(aliquota15);
                    tasse = tasseFino16000.add(tasseSopra16000);
                } else {
                    BigDecimal tasseFino16000 = soglia16000.multiply(aliquota10);
                    BigDecimal tasseFino64000 = soglia64000.subtract(soglia16000).multiply(aliquota15);
                    BigDecimal tasseSopra64000 = ral.subtract(soglia64000).multiply(aliquota25);
                    tasse = tasseFino16000.add(tasseFino64000).add(tasseSopra64000);
                }
            } else {
                // Tassazione per non coniugati
                final BigDecimal soglia8000 = BigDecimal.valueOf(8000);
                final BigDecimal soglia32000 = BigDecimal.valueOf(32000);

                final BigDecimal aliquota10 = BigDecimal.valueOf(0.10);
                final BigDecimal aliquota15 = BigDecimal.valueOf(0.15);
                final BigDecimal aliquota25 = BigDecimal.valueOf(0.25);

                if (ral.compareTo(soglia8000) <= 0) {
                    tasse = ral.multiply(aliquota10);
                } else if (ral.compareTo(soglia32000) <= 0) {
                    BigDecimal tasseFino8000 = soglia8000.multiply(aliquota10);
                    BigDecimal tasseSopra8000 = ral.subtract(soglia8000).multiply(aliquota15);
                    tasse = tasseFino8000.add(tasseSopra8000);
                } else {
                    BigDecimal tasseFino8000 = soglia8000.multiply(aliquota10);
                    BigDecimal tasseFino32000 = soglia32000.subtract(soglia8000).multiply(aliquota15);
                    BigDecimal tasseSopra32000 = ral.subtract(soglia32000).multiply(aliquota25);
                    tasse = tasseFino8000.add(tasseFino32000).add(tasseSopra32000);
                }
            }
        }
        return tasse;
    }

    private static void stampaTabella(BigDecimal ral, BigDecimal tasse, BigDecimal netto) {
        System.out.println("-----------------------------------------");
        System.out.printf("%-10s %-10s %-10s%n", "RAL", "Tasse", "Netto");
        System.out.println("-----------------------------------------");
        System.out.printf("%-10s %-10s %-10s%n", ral, tasse, netto);
        System.out.println("-----------------------------------------");
    }
}