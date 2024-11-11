package academy.esercizi.esercizi_18.esercizio_18_2;

public class Studente {
    private String nome;
    private double punteggioTot;
    private int numeroDiQuizSvolti;
    private double mediaVoti;


    public Studente(String nome) {
        this.nome = nome;
        this.punteggioTot = 0;
        this.numeroDiQuizSvolti = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPunteggioTot() {
        return punteggioTot;
    }

    public void setPunteggioTot(double punteggioTot) {
        this.punteggioTot = punteggioTot;
    }


    public void addQuiz(int punteggio) {
        punteggioTot += punteggio;
        numeroDiQuizSvolti++;
    }

    public void mediaPunteggiQuiz() {
        mediaVoti = punteggioTot / numeroDiQuizSvolti;
    }


    @Override
    public String toString() {
        mediaPunteggiQuiz();
        System.out.printf("| %-20s | %-20s | %-22s | %-15s |\n", "Nome","Punteggio totale", "Numero di quiz svolti", "Media voti");
        System.out.printf("| %-20s | %-20.2f | %-22d | %-15.2f |\n", nome, punteggioTot, numeroDiQuizSvolti, mediaVoti);
        return "";
    }
}
