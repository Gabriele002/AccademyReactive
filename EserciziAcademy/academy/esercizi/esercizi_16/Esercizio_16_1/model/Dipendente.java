package academy.esercizi.esercizi_16.Esercizio_16_1.model;

public class Dipendente {
    private String nome;
    private double stipendio;

    public Dipendente(String nome, double stipendio) {
        this.nome = nome;
        this.stipendio = stipendio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }

    public void aumentaStipendio(int percentuale) {
        double stipendioTotale = stipendio * (1 + percentuale / 100.0);
        setStipendio(stipendioTotale);
    }

    public void stampaDipendente(Dipendente dipendente) {
        System.out.printf("%-20s %-15s%n", "Nome", "Stipendio");
        System.out.println("------------------------------");
        System.out.printf("%-20s %-15.2f%n", dipendente.getNome(), dipendente.getStipendio());
    }


}
