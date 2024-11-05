package academy.esercizi.esercizio_36.Esercizio_36_bis;

import academy.esercizi.esercizi_30.*;

public class Esercizio_36_bis {
    public static void main(String[] args) {
        Esercizio_36_bis esercizio_36_bis = new Esercizio_36_bis();
        esercizio_36_bis.soluzione();
    }

    public void soluzione() {
        Agenda ag1 = new Agenda(Giorni.MARTEDI, Impegni.TEATRO);
        Agenda ag2 = new Agenda(Giorni.LUNEDI, Impegni.CALCETTO);
        Agenda ag3 = new Agenda(Giorni.DOMENICA, Impegni.DIVANO);
        Agenda ag4 = new Agenda(Giorni.LUNEDI, Impegni.TEATRO);

        System.out.println(PrioritaAttivita.confrontaPriorita(ag1, ag2));
        System.out.println(PrioritaAttivita.confrontaPriorita(ag1, ag3));
        System.out.println(PrioritaAttivita.confrontaPriorita(ag2, ag3));
        System.out.println(PrioritaAttivita.confrontaPriorita(ag1, ag4));
        System.out.println(PrioritaAttivita.confrontaPriorita(ag2, ag4));

    }
}



