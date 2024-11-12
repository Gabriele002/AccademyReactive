package academy.esercizi.esercizi_20.esercizio_20_1;

public class Esercizio_20_1 {

    public static void main(String[] args) {
        Esercizio_20_1 esercizio_20_1 = new Esercizio_20_1();
        esercizio_20_1.soluzione();
    }

    private void soluzione() {
        Persona persona = new Persona("Gabriele");
        persona.aggiungiAmico("Pippo Topolino Giovanni");
        System.out.println(persona.numeroAmici());
        persona.togliAmico("Topolino");
        System.out.println(persona.getFriendNames());

    }
}
