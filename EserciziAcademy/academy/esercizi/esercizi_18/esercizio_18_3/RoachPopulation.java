package academy.esercizi.esercizi_18.esercizio_18_3;

public class RoachPopulation {
    private int scarafaggi;
    private int intervalloRiproduzione;

    public RoachPopulation(int popolazioneIniziale, int intervalloRiproduzione) {
        this.scarafaggi = popolazioneIniziale;
        this.intervalloRiproduzione = intervalloRiproduzione;
    }

    public void riproduci() {
        scarafaggi *= 2;
    }

    public void spruzza(double percentuale) {
        scarafaggi -= (int)(scarafaggi * percentuale / 100);
    }

    public int getScarafaggi() {
        return scarafaggi;
    }

    public void simula(int anni) {
        for (int anno = 1; anno <= anni; anno++) {
            System.out.println("Anno " + anno + ": Popolazione attuale = " + getScarafaggi());
            if (anno == 6){
                spruzza(10);
                System.out.println("Popolazione dopo la spruzzata: " + getScarafaggi());
            }
            if (anno % intervalloRiproduzione == 0) {
                riproduci();
                System.out.println("La popolazione si e raddoppiata: " + getScarafaggi());
            }
        }
    }}
