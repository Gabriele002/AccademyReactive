package academy.esercizi.esercizi_15.esercizio_15_1;

import java.math.BigDecimal;

public class TimeDeposit {
    final BigDecimal interessi;
    private BigDecimal saldoIniziale;

    public TimeDeposit() {
        this.interessi = new  BigDecimal(4);
        this.saldoIniziale = new BigDecimal(1000);
    }

    public BigDecimal getInteressi() {
        return interessi;
    }

    public BigDecimal getSaldoIniziale() {
        return saldoIniziale;
    }

    private void setSaldoIniziale(BigDecimal saldoIniziale) {
        this.saldoIniziale = saldoIniziale;
    }


    public void accreditaInteressi(){
        BigDecimal interessiAccumulati = (saldoIniziale.multiply(interessi)).divide(new BigDecimal(100));
        setSaldoIniziale(saldoIniziale.add(interessiAccumulati));
    }

    public void prelevaTutto(){
        System.out.println("Hai prelevato: " + saldoIniziale);
        setSaldoIniziale(saldoIniziale.subtract(saldoIniziale));
        System.out.println("Ti rimangono: "  + saldoIniziale);
    }

    @Override
    public String toString() {
        return "Conto: " +
                "interessi=" + interessi + "%" +
                ", saldoIniziale=" + saldoIniziale;
    }
}
