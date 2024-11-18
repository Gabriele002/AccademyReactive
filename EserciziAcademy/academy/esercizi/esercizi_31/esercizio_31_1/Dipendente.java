package academy.esercizi.esercizi_31.esercizio_31_1;

import java.math.BigDecimal;

public class Dipendente {

    private BigDecimal stipendio;
    private String nome;

    public Dipendente(BigDecimal stipendio, String nome) {
        this.stipendio = stipendio;
        this.nome = nome;
    }

    public BigDecimal getStipendio() {
        return stipendio;
    }

    public void setStipendio(BigDecimal stipendio) {
        this.stipendio = stipendio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal bustaPaga(){
        return stipendio;
    }
}
