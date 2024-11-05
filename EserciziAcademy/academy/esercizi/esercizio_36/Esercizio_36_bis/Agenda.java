package academy.esercizi.esercizio_36.Esercizio_36_bis;

public class Agenda implements Comparable<Agenda>{
    Giorni giornoDellaSettimana;
    Impegni impegno;


    public Agenda(Giorni giornoDellaSettimana, Impegni impegno) {
        this.giornoDellaSettimana = giornoDellaSettimana;
        this.impegno = impegno;
    }

    @Override
    public int compareTo(Agenda o) {
       return this.giornoDellaSettimana.compareTo(o.giornoDellaSettimana);
    }
}


