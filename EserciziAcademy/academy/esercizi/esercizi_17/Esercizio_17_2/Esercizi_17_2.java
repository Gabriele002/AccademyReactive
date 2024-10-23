package academy.esercizi.esercizi_17.Esercizio_17_2;


import academy.esercizi.esercizi_17.Esercizio_17_2.model.Microonde;

public class Esercizi_17_2 {
    public static void main(String[] args) {
        Esercizi_17_2 esercizio_17_2 = new Esercizi_17_2();
        esercizio_17_2.test();
    }

    public void test(){
        Microonde microonde = new Microonde();
        microonde.cuociPatate();
        System.out.println("------------");
        microonde.resettaMicroonde();
        System.out.println("------------");
        microonde.aumentaTimer30Secondi();
        microonde.aumentaTimer30Secondi();
        System.out.println("------------");
        microonde.avviaMicroonde();
    }
}