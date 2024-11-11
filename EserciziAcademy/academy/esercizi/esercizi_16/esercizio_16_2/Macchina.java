package academy.esercizi.esercizi_16.esercizio_16_2;

public class Macchina {

    private double litriCarburante;
    private double consumoPerKm;

    public Macchina(double consumoPerLitro) {
        this.litriCarburante = 0;
        this.consumoPerKm = consumoPerLitro;
    }

    public void getLitriCarburante() {
        System.out.println("Litri di carburante presenti nel serbatoio; " + litriCarburante);
    }

    public void setLitriCarburante(double litriCarburante) {
        this.litriCarburante = litriCarburante;
    }


    public void simulaTragitto(int distanza) {
        double carburanteNecessario = consumoPerKm * distanza;
        if (carburanteNecessario > litriCarburante) {
            System.out.println("Non c'è abbastanza carburante per percorrere questa distanza, rifornisci di almeno: " + (carburanteNecessario - litriCarburante) + "l");
        } else {
            litriCarburante -= carburanteNecessario;
            System.out.println("Simulazione viaggio per una distanza di: " + distanza + "km carburante necessario per il tragito: " + carburanteNecessario + "l");
            System.out.println("Carburante rimasto: " + litriCarburante);
        }
    }

    public void addCarburatne(int litriDaAggiungere) {
        litriCarburante = litriCarburante + litriDaAggiungere;
        System.out.println("Sono stati aggiunti: " + litriDaAggiungere + " totale litri: " + litriCarburante + "l");
    }


    @Override
    public String toString() {
        return "Macchina " +
                "litriCarburante= " + litriCarburante +
                ", consumoPerKm= " + consumoPerKm;
    }
}
