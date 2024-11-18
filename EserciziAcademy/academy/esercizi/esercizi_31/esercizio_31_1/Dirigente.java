package academy.esercizi.esercizi_31.esercizio_31_1;

import java.math.BigDecimal;

public class Dirigente extends Dipendente{

    private BigDecimal premioDiProduzione;

    public Dirigente(BigDecimal stipendio, String nome) {
        super(stipendio, nome);
    }

    public BigDecimal getPremioDiProduzione() {
        return premioDiProduzione;
    }

    public void setPremioDiProduzione(BigDecimal premioDiProduzione) {
        this.premioDiProduzione = premioDiProduzione;
    }

    @Override
    public BigDecimal bustaPaga(){
        return super.bustaPaga().add(premioDiProduzione);
    }


    @Override
    public String toString() {
        return "Dirigente " + super.getNome() +
                " Premio di produzione " + premioDiProduzione + "$" +
                " stipendio: " + bustaPaga() + "$";
    }
}
