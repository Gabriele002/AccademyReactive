package academy.esercizi.biblioteca;

public class Utente {


    private String nome;
    private String cognome;
    private int id;
    private static int counter = 0;


    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Utente " +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", id=" + id ;
    }
}
