package academy.esercizi.esercizi_29.esercizio_29_2;


import java.util.HashSet;
import java.util.Set;

public class Cliente {
    private String nome;
    private double importo;
    private Set<Integer> negoziVisitati;

    public Cliente(String name) {
        this.nome = name;
        this.importo = 0;
        this.negoziVisitati = new HashSet<>();
    }


    public void setImporto(double importo) {
        this.importo = importo;
    }

    public void acquista(double prezzo, int numeroNegozio) {
        this.negoziVisitati.add(numeroNegozio);
        System.out.println("Acquisto effettuato presso il negozio " + numeroNegozio);
        if (scontoRaggiunto()) {
            System.out.println("Sconto raggiunto! L'importo totale è superiore a 100 euro e hai visitato almeno 3 negozi.");
            double amountSale = prezzo - 10;
            System.out.println("Sconto applicato: €10. Prezzo finale per questo acquisto: €" + amountSale);
            this.importo += amountSale;
        } else {
            System.out.println("Sconto non raggiunto. Prezzo finale per questo acquisto: €" + prezzo);
            this.importo += prezzo;
        }

        System.out.println("Importo totale attuale: €" + this.importo);
    }

    public boolean scontoRaggiunto(){
        return this.importo >= 100 && negoziVisitati.size() >= 3;
    }

    @Override
    public String toString() {
        return "Cliente " +
                "nome='" + nome + '\'' +
                ", importo=" + importo +
                ", negoziVisitati=" + negoziVisitati;
    }
}

