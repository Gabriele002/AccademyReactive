package academy.esercizi.esercizi_29.esercizio_29_1;

public class Giocatore {
    private String nome;
    private int punteggio;

    public Giocatore(String nome) {
        this.nome = nome;
        this.punteggio = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void aumentoPunteggio(){
        this.punteggio += 1;
    }


    @Override
    public String toString() {
        return nome + " ha totalizato un totale di " + punteggio + " punti.";
    }
}
