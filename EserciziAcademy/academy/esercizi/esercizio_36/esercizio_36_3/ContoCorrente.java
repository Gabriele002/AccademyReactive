package academy.esercizi.esercizio_36.esercizio_36_3;

public class ContoCorrente {
    private double saldo;
    private String numeroConto;

    public ContoCorrente(double saldo, String numeroConto) {
        this.saldo = saldo;
        this.numeroConto = numeroConto;
    }

    private void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroConto() {
        return numeroConto;
    }

    public void versa(double importo) {
        try {
            if (importo > 1000) {
                throw new ImportoSuperioreAMilleException("Impossibile versare più di 1000 euro.");
            }
            saldo += importo;
            System.out.println("Versamento di " + importo + " euro effettuato. Saldo attuale: " + saldo + " euro.");
        } catch (ImportoSuperioreAMilleException e) {
            System.out.println(e.getMessage()); ;
        }
    }


    public void preleva(double importo) {
        try {
            if (importo > saldo) {
                throw new PrelievoNonValidoException(saldo, numeroConto, importo);
            }
            saldo -= importo;
            System.out.println("Prelievo di " + importo + " euro effettuato. Saldo attuale: " + saldo + " euro.");
        } catch (PrelievoNonValidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
