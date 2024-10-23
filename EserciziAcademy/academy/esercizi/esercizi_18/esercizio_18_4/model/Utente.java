package academy.esercizi.esercizi_18.esercizio_18_4.model;

public class Utente {

    private String nome;
    private ContoCorrente contoCorrente;
    private ContoDiRisparmio contoDiRisparmio;

    public Utente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ContoCorrente getContoCorrente() {
        return contoCorrente;
    }

    public void setContoCorrente(ContoCorrente contoCorrente) {
        this.contoCorrente = contoCorrente;
    }

    public ContoDiRisparmio getContoDiRisparmio() {
        return contoDiRisparmio;
    }

    public void setContoDiRisparmio(ContoDiRisparmio contoDiRisparmio) {
        this.contoDiRisparmio = contoDiRisparmio;
    }
}
