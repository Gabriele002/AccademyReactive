package academy.esercizi.esercizi_18.esercizio_18_4.model;

import java.math.BigDecimal;

public class ContoCorrente {
    private BigDecimal saldoIniziale;
    private Utente utente;

    public ContoCorrente(BigDecimal saldoIniziale, Utente utente) {
        this.saldoIniziale = saldoIniziale;
        this.utente = utente;
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
        System.out.printf("%-20s %-20s %-30s\n", "Saldo Iniziale", "Soldi da Prelevare", "Soldi sul Conto");

        if (saldoIniziale.compareTo(soldiDaPrelevare) >= 0) {
            BigDecimal saldoFinale = saldoIniziale.subtract(soldiDaPrelevare);
            System.out.printf("%-20s %-20s %-30s\n", saldoIniziale.toString(), soldiDaPrelevare.toString(), saldoFinale.toString());
        } else {
            System.out.printf("%-20s %-20s %-30s\n", saldoIniziale.toString(), soldiDaPrelevare.toString(), "Cifra maggiore del saldo disponibile");
        }
    }

}
