package academy.esercizi.esercizi_17.Esercizio_17_2.model;

public class Microonde {
    private int secondi;
    private int potenza;

    public Microonde() {
        this.secondi = 0;
        this.potenza = 1;
    }

    // Metodi per il timer
    private void resettaTimer() {
        this.secondi = 0;
    }

    public void impostaTimer(int secondi) {
        if (secondi >= 0) {
            this.secondi = secondi;
            System.out.printf("Timer impostato a %d secondi.%n", this.secondi);
        } else {
            System.out.println("Il tempo deve essere maggiore o uguale a 0.");
        }
    }

    public void aumentaTimer30Secondi() {
        this.secondi += 30;
        System.out.printf("Timer aumentato di 30 secondi. Tempo attuale: %d secondi%n", this.secondi);
    }

    // Metodo per settare la potenza
    public void setPotenza(int potenza) {
        if (potenza < 1 || potenza > 2) {
            System.out.println("Potenza non valida. Utilizzare 1 o 2.");
            return;
        }
        this.potenza = potenza;
        System.out.printf("Potenza impostata a %d.%n", this.potenza);
    }

    public void avviaMicroonde() {
        System.out.println("Avvio del microonde...");
        if (secondi > 0) {
            System.out.printf("Microonde avviato per %d secondi a potenza %d.%n", secondi, potenza);
        } else {
            System.out.println("Imposta il timer prima di avviare il microonde.");
        }
    }

    public void cuociPollo() {
        System.out.println("Preparazione per cuocere il pollo...");
        setPotenza(2);
        impostaTimer(120);
    }

    public void cuociPatate() {
        System.out.println("Preparazione per cuocere le patate...");
        impostaTimer(200);
        setPotenza(1);
    }

    public void resettaMicroonde() {
        setPotenza(1);
        resettaTimer();
        System.out.println("Microonde resettato. Potenza: 1, Timer: 0 secondi.");
    }
}
