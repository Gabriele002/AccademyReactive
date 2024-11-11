package academy.esercizi.esercizi_12.esercizio_12_1;


public class Esercizio_12_1 {
    public static void main(String[] args) {
        Esercizio_12_1 esercizio_12_1 = new Esercizio_12_1();
        esercizio_12_1.soluzione();

    }

    private void soluzione() {
        String[][] pratoPopolato = Prato.popolaPrato();
        Robot robot = new Robot(pratoPopolato);
        System.out.println("Stato iniziale del prato:");
        robot.stampaPratoTagliato();

        robot.muoviGiu(pratoPopolato);
        robot.muoviGiu(pratoPopolato);
        robot.muoviDestra(pratoPopolato);
        robot.muoviDestra(pratoPopolato);
        robot.muoviDestra(pratoPopolato);
        robot.muoviDestra(pratoPopolato);
        robot.muoviDestra(pratoPopolato);


        System.out.println("Stato del prato dopo i movimenti del robot:");
        robot.stampaPratoTagliato();
    }

}
