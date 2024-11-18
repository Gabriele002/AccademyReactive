package academy.esercizi.esercizi_31.esercizio_31_1;

import academy.esercizi.esercizi_17.esercizio_17_1.Direzione;

import java.math.BigDecimal;

public class Esercizio_31_1 {
    public static void main(String[] args) {
        Esercizio_31_1 esercizio_31_1 = new Esercizio_31_1();
        esercizio_31_1.soluzione();
    }

    private void soluzione() {
        Dirigente pippo = new Dirigente(new BigDecimal(1000),"Pippo");
        Dirigente gabriele = new Dirigente(new BigDecimal(1200), "Gabriele");

        pippo.setPremioDiProduzione(new BigDecimal(100));
        gabriele.setPremioDiProduzione(new BigDecimal(310));

        System.out.println(pippo);
        System.out.println(gabriele);
    }
}
