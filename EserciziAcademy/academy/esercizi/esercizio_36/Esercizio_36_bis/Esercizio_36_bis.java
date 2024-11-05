package academy.esercizi.esercizio_36.Esercizio_36_bis;

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

        PrioritaAttivita.confrontaPriorita(ag1, ag2);
        PrioritaAttivita.confrontaPriorita(ag1, ag3);
        PrioritaAttivita.confrontaPriorita(ag2, ag3);
        PrioritaAttivita.confrontaPriorita(ag1, ag4);
        PrioritaAttivita.confrontaPriorita(ag2, ag4);

        ImplementazioneComparetor(ag1,ag2);

    }

    private static void ImplementazioneComparetor (Agenda o1, Agenda o2){
        ComparetorImpegno comparetorImpegno = new ComparetorImpegno();
        if (comparetorImpegno.compare(o1, o2) > 0){
            System.out.printf("%s %s %s", "L'impegno " + o1.impegno, "è più interessante dell'impegno", "di " + o2.impegno);
        } else if (comparetorImpegno.compare(o1, o2) < 0) {
            System.out.printf("%s %s %s", "L'impegno " + o2.impegno, "è più interessante dell'impegno", "di " + o1.impegno);
        } else {
            System.out.println("Hanno la stessa importanza");
        }

    }
}



