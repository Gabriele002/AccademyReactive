package academy.esercizi.esercizi_18.esercizio_18_4.model;


import academy.esercizi.utility.Validificazione;

import java.math.BigDecimal;


public class Conto {
    private BigDecimal saldoIniziale;
    private Utente utente;

    public Conto(BigDecimal saldoIniziale) {
        this.saldoIniziale = saldoIniziale;
    }

    public BigDecimal getSaldoIniziale() {
        return saldoIniziale;
    }

    public void setSaldoIniziale(BigDecimal saldoIniziale) {
        this.saldoIniziale = saldoIniziale;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void preleva(BigDecimal soldiDaPrelevare) {
        boolean validificazione = Validificazione.validificaPrelievo(saldoIniziale, soldiDaPrelevare);
        if (!validificazione) {
            System.out.println(validificazione);
            System.out.printf("%-20s %-20s %-30s\n", "Saldo Iniziale", "Soldi da Prelevare", "Soldi sul Conto");
            BigDecimal saldoFinale = saldoIniziale.subtract(soldiDaPrelevare);
            System.out.printf("%-20s %-20s %-30s\n", saldoIniziale, soldiDaPrelevare.toString(), saldoFinale);
        }
    }


    public void deposita(BigDecimal soldiDaDepositare) {
        System.out.printf("%-20s %-20s %-30s\n", "Saldo Iniziale", "Soldi depositati", "Soldi sul Conto");
        BigDecimal saldoFinale = saldoIniziale.add(soldiDaDepositare);
        System.out.printf("%-20s %-20s %-30s\n", saldoIniziale, soldiDaDepositare.toString(), saldoFinale);

    }

    public void bonifico(Conto conto, BigDecimal importo) {
        if (importo.compareTo(saldoIniziale) <= 0) {
            this.setSaldoIniziale(this.getSaldoIniziale().subtract(importo));
            conto.setSaldoIniziale(conto.getSaldoIniziale().add(importo));
            System.out.printf("Bonifico di %.2f€ a %s completato.\n", importo, conto.utente);
            System.out.printf("Bonifico di %.2f€ a %s completato. Saldo attuale: %.2f€\n", importo, conto.utente, conto.getSaldoIniziale());
        } else {
            System.out.println("Saldo insufficiente per il bonifico.");
        }
    }

}
