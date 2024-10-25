package academy.esercizi.esercizi_1;

import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.ArrayList;

public class Esercizio_1_4 {
    public static void main(String[] args) {
        Esercizio_1_4 esercizio_1_4 = new Esercizio_1_4();
        esercizio_1_4.soluzione();
    }
    public void soluzione(){
        System.out.printf("%-30s %-10s %n", "Data di compleanno", "Nome");
        for( compleanniAmici compleanni : compleanniAmici.values() ) {
            System.out.printf("%-30s %-10s %n", compleanni.compleanno, compleanni.nome);
            }
    }

    public enum compleanniAmici {

        COMPLEANNO_GIUSEPPE  ("Giuseppe", LocalDate.of(2002,10,23) ),
        COMPLEANNO_GABRIELE ("Gabriele",  LocalDate.of(2004,6,20) ),
        COMPLEANNO_DARIO("Dario", LocalDate.of(1999,12,5) ),
        COMPLEANNO_IRENE("Irene", LocalDate.of(2002,10,23) ),
        COMPLEANNO_DANIELE("Daniele", LocalDate.of(2000,4,12) );

        private String nome;
        private LocalDate compleanno;

        compleanniAmici(String nome, LocalDate compleanno) {
            this.nome = nome;
            this.compleanno = compleanno;
        }
    }
}
