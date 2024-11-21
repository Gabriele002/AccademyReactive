package academy.esercizi.esercizio_39;

public class Sportello {
    private boolean statoSportello;
    private String nome;

    public Sportello(String nome) {
        this.statoSportello = true;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public boolean isLibero() {
        return statoSportello;
    }

    public void setStatoSportello(boolean statoSportello) {
        this.statoSportello = statoSportello;
    }

    public void cambiaStato(){
        this.statoSportello = statoSportello ? false : true;
    }

}