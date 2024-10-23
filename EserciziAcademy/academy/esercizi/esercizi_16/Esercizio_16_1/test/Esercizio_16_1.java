package academy.esercizi.esercizi_16.Esercizio_16_1.test;

import academy.esercizi.esercizi_16.Esercizio_16_1.model.Dipendente;

public class Esercizio_16_1 {
    public static void main(String[] args) {
        Esercizio_16_1 esercizio_16_1 = new Esercizio_16_1();
        esercizio_16_1.test();
    }

    private void test(){
        Dipendente dipendenteGabriele = new Dipendente("Gabriele", 1000.00);
        System.out.println("Stipendio prima dell aumento");
        dipendenteGabriele.stampaDipendente(dipendenteGabriele);

        System.out.println("Stipendio dopo l aumento del 10%");
        dipendenteGabriele.aumentaStipendio(10);
        dipendenteGabriele.stampaDipendente(dipendenteGabriele);
    }

}
