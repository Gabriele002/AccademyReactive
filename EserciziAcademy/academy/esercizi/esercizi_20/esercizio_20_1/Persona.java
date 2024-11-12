package academy.esercizi.esercizi_20.esercizio_20_1;


public class Persona {
    private String nome;
    private StringBuilder amici;

    public Persona(String name) {
        this.nome = name;
        this.amici = new StringBuilder();
    }

    public void aggiungiAmico(String nomeAmico) {
        if (amici.length() > 0) {
            amici.append(" ");
        }
        amici.append(nomeAmico);
    }

    public void togliAmico(String nomeAmico) {
        int index = amici.indexOf(nomeAmico);
        int endIndex = index + nomeAmico.length();
        amici.delete(index, endIndex);
        System.out.println("Eliminato l amico: " + nomeAmico);
    }

    public String getFriendNames() {
        return amici.toString();
    }

    public int numeroAmici() {
        if (amici.length() == 0) {
            return 0;
        }
        return amici.toString().split(" ").length;
    }

    public String getNome() {
        return nome;
    }
}


