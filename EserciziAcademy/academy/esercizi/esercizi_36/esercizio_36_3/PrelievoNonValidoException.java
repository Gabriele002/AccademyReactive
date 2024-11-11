package academy.esercizi.esercizi_36.esercizio_36_3;

public class PrelievoNonValidoException extends Exception {

    public PrelievoNonValidoException(double saldoCorrente, String numeroConto, double importo) {
        super("Prelievo non possibile per il conto: " + numeroConto + "\n" + "Saldo corrente: " + saldoCorrente + " importo da prelevare  " + importo);
    }
}

