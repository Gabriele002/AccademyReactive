package academy.esercizi.esercizi_19.esercizio_19_1;

public class Minivan {
    private Interruttore interruttoreDiSinistra;
    private Interruttore interruttoreDiDestra;
    private Maniglia manigliaInternaDiSinistra;
    private Maniglia manigliaInternaDiDestra;
    private Maniglia manigliaEsternaDiSinistra;
    private Maniglia manigliaEsternaDiDestra;
    private Interruttore interruttoreBambini;
    private Interruttore interruttoreGenerale;
    private Marcia marcia;

    public Interruttore getInterruttoreDiSinistra() {
        return interruttoreDiSinistra;
    }

    public void setInterruttoreDiSinistra(Interruttore interruttoreDiSinistra) {
        this.interruttoreDiSinistra = interruttoreDiSinistra;
    }

    public Interruttore getInterruttoreDiDestra() {
        return interruttoreDiDestra;
    }

    public void setInterruttoreDiDestra(Interruttore interruttoreDiDestra) {
        this.interruttoreDiDestra = interruttoreDiDestra;
    }

    public Maniglia getManigliaInternaDiSinistra() {
        return manigliaInternaDiSinistra;
    }

    public void setManigliaInternaDiSinistra(Maniglia manigliaInternaDiSinistra) {
        this.manigliaInternaDiSinistra = manigliaInternaDiSinistra;
    }

    public Maniglia getManigliaInternaDiDestra() {
        return manigliaInternaDiDestra;
    }

    public void setManigliaInternaDiDestra(Maniglia manigliaInternaDiDestra) {
        this.manigliaInternaDiDestra = manigliaInternaDiDestra;
    }

    public Maniglia getManigliaEsternaDiSinistra() {
        return manigliaEsternaDiSinistra;
    }

    public void setManigliaEsternaDiSinistra(Maniglia manigliaEsternaDiSinistra) {
        this.manigliaEsternaDiSinistra = manigliaEsternaDiSinistra;
    }

    public Maniglia getManigliaEsternaDiDestra() {
        return manigliaEsternaDiDestra;
    }

    public void setManigliaEsternaDiDestra(Maniglia manigliaEsternaDiDestra) {
        this.manigliaEsternaDiDestra = manigliaEsternaDiDestra;
    }

    public Interruttore getInterruttoreBambini() {
        return interruttoreBambini;
    }

    public void setInterruttoreBambini(Interruttore interruttoreBambini) {
        this.interruttoreBambini = interruttoreBambini;
    }

    public Interruttore getInterruttoreGenerale() {
        return interruttoreGenerale;
    }

    public void setInterruttoreGenerale(Interruttore interruttoreGenerale) {
        this.interruttoreGenerale = interruttoreGenerale;
    }

    public Marcia getMarcia() {
        return marcia;
    }

    public void setMarcia(Marcia marcia) {
        this.marcia = marcia;
    }

    public Minivan() {
        this.interruttoreDiSinistra = Interruttore.SPENTO;
        this.interruttoreDiDestra = Interruttore.SPENTO;
        this.manigliaInternaDiSinistra = Maniglia.CHIUSA;
        this.manigliaInternaDiDestra = Maniglia.CHIUSA;
        this.manigliaEsternaDiSinistra = Maniglia.CHIUSA;
        this.manigliaEsternaDiDestra = Maniglia.CHIUSA;
        this.interruttoreBambini = Interruttore.SPENTO;
        this.interruttoreGenerale = Interruttore.SPENTO;
        this.marcia = Marcia.P;
    }

    public String verificaPortaSinistra() {
        boolean portaSinistraPuoAprirsi = (controlloBambini() && controlloGenerale() && controllaMarcia());

        if (portaSinistraPuoAprirsi) {
            return "la porta sinistra si apre";
        } else {
            return "la porta sinistra resta chiusa";
        }
    }

    public String verificaPortaDestra() {
        boolean portaDestraPuoAprirsi = (controlloBambini() && controlloGenerale() && controllaMarcia());

        if (portaDestraPuoAprirsi) {
            return "la porta destra si apre";
        } else {
            return "la porta destra resta chiusa";
        }
    }

    public boolean controllaMarcia() {
        if (!marcia.equals(Marcia.P)){
            System.out.println("Veicolo in movimento impossibilie aprire le portiere");
            return false;
        }
        return true;
    }

    public boolean controlloBambini(){
        if (!interruttoreBambini.equals(Interruttore.SPENTO)){
            System.out.println("Il controllo bambini e accesso impossibile aprire la porta");
            return false;
        }
        return true;
    }

    public boolean controlloGenerale() {
        if (!interruttoreGenerale.equals(Interruttore.SPENTO)){
            System.out.println("Il controllo generale e accesso impossibile aprire la porta");
            return false;
        }
        return true;
    }
}

