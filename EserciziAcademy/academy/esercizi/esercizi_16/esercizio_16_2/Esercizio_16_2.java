package academy.esercizi.esercizi_16.esercizio_16_2;


public class Esercizio_16_2 {
    public static void main(String[] args) {
        Esercizio_16_2 esercizio_16_2 = new Esercizio_16_2();
        esercizio_16_2.soluzione();
    }

    private void soluzione() {
        Macchina macchina = new Macchina(2);
        macchina.setLitriCarburante(100);
        macchina.addCarburatne(200);
        macchina.simulaTragitto(100);
        System.out.println("--------------------");
        macchina.simulaTragitto(120);


    }


}
