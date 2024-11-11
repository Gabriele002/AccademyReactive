package academy.esercizi.esercizi_36.Esercizio_36_bis;

public class PrioritaAttivita {

    public static int getPriorita(Impegni priorita) {
        switch (priorita) {
            case DIVANO: return 1;
            case CALCETTO: return 2;
            case TEATRO: return 3;

        }
        return 0;
    }

    public static void confrontaPriorita(Agenda ag1, Agenda ag2) {
        int priorita1 = getPriorita(ag1.impegno);
        int priorita2 = getPriorita(ag2.impegno);

        String agenda1String = ag1.giornoDellaSettimana + " - " + ag1.impegno;
        String agenda2String = ag2.giornoDellaSettimana + " - " + ag2.impegno;

        if (priorita1 > priorita2) {
            System.out.println("L'agenda "  + agenda1String + " e piu importante dell'agenda " + agenda2String);
        } else if (priorita1 < priorita2) {
            System.out.println("L'agenda "  + agenda2String + " e piu importante dell'agenda " + agenda1String);
        } else {
            System.out.println("L'agenda "  + agenda2String + " e " + agenda1String + " sono importanti uguali");
        }
    }

}

