package academy.esercizi.esercizi_37.Esercizio_37_5;

import java.util.Comparator;

public class SuperficeComparable implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        if (o1.getSuperficia() > o2.getSuperficia()){
            return 1;
        } else if (o1.getSuperficia() < o2.getSuperficia()) {
            return -1;
        }
        return 0;
    }
}
