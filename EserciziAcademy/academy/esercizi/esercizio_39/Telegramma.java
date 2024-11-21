package academy.esercizi.esercizio_39;

public class Telegramma implements Runnable {
    private String testo;
    private UfficioPostale ufficioPostale;

    public Telegramma(String testo, UfficioPostale ufficioPostale) {
        this.testo = testo;
        this.ufficioPostale = ufficioPostale;
    }

    @Override
    public void run() {
        try {
            System.out.println("TELEGRAMMA");
            Sportello sportelloLibero = ufficioPostale.getSportelloLibero();
            System.out.println("Buongiorno" + sportelloLibero.getNome() + ", dovrei spedire un telegramma");
            Thread.sleep(3000);
            System.out.println("Il telegramma " + testo + " è stato inviato da " + sportelloLibero.getNome());
            sportelloLibero.cambiaStato();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}