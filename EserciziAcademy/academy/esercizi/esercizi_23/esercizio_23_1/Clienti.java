package academy.esercizi.esercizi_23.esercizio_23_1;

public class Clienti {
    private String nome;
    private int numeroDiFilmNoleggiati;
    private int numeroDiPersoneInvitate;
    private int sconto;


    public Clienti(String nome) {
        this.nome= nome;
        this.numeroDiFilmNoleggiati = 0;
        this.numeroDiPersoneInvitate = 0;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroDiFilmNoleggiati() {
        return numeroDiFilmNoleggiati;
    }

    public void setNumeroDiFilmNoleggiati(int numeroDiFilmNoleggiati) {
        this.numeroDiFilmNoleggiati = numeroDiFilmNoleggiati;
    }

    public int getNumeroDiPersoneInvitate() {
        return numeroDiPersoneInvitate;
    }

    public void setNumeroDiPersoneInvitate(int numeroDiPersoneInvitate) {
        this.numeroDiPersoneInvitate = numeroDiPersoneInvitate;
    }

    public void aggiungiFilmNoleggiato(){
        numeroDiFilmNoleggiati++;
    }

    public void aggiungiAmicoInvitato(){
        numeroDiPersoneInvitate++;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    public void calcolaSconto(){
        int calcolaSconto = numeroDiFilmNoleggiati + numeroDiPersoneInvitate;
        setSconto(Math.min(calcolaSconto, 75));
    }

    @Override
    public String toString() {
        return "Cliente " +
                "nome='" + nome + '\'' +
                ", numeroDiFilmNoleggiati=" + numeroDiFilmNoleggiati +
                ", numeroDiPersoneInvitate=" + numeroDiPersoneInvitate +
                ", sconto=" + sconto;
    }
}
