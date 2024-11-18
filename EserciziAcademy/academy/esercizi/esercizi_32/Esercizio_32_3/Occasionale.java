package academy.esercizi.esercizi_32.Esercizio_32_3;

public class Occasionale extends Appuntamento{

    private int giorno;
    private int mese;
    private int anno;

    public Occasionale(String descrizione, int giorno, int mese, int anno) {
        super(descrizione);
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

}
