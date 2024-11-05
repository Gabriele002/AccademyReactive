package academy.esercizi.esercizio_36.Esercizio_36_bis;

public class PrioritaAttivita {

    public static int getPriorita(Impegni priorita) {
        switch (priorita) {
            case DIVANO: return 1;
            case CALCETTO: return 2;
            case TEATRO: return 3;

        }
        return 0;
    }

    public static String confrontaPriorita(Agenda ag1, Agenda ag2) {
        int priorita1 = getPriorita(ag1.impegno);
        int priorita2 = getPriorita(ag2.impegno);

        String agenda1String = ag1.giornoDellaSettimana + " - " + ag1.impegno;
        String agenda2String = ag2.giornoDellaSettimana + " - " + ag2.impegno;

        if (priorita1 > priorita2) {
            return String.format("L'agenda '%s' è più importante dell'agenda '%s'", agenda1String, agenda2String);
        } else if (priorita1 < priorita2) {
            return String.format("L'agenda '%s' è più importante dell'agenda '%s'", agenda2String, agenda1String);
        } else {
            return String.format("Le agende '%s' e '%s' hanno la stessa importanza", agenda1String, agenda2String);
        }
    }

}

