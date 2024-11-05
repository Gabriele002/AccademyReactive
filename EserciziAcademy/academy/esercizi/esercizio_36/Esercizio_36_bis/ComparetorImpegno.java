package academy.esercizi.esercizio_36.Esercizio_36_bis;

import java.util.Comparator;

public class ComparetorImpegno implements Comparator<Agenda> {

    @Override
    public int compare(Agenda o1, Agenda o2) {
        if (o1.impegno.ordinal() < o2.impegno.ordinal()){
            return 1;

        } else if (o1.impegno.ordinal() > o2.impegno.ordinal()){
            return -1;
        } else {
            return 0;
        }
    }
}
