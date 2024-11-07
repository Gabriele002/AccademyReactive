package academy.esercizi.esercizi_12.esercizio_12_1;

public class TagliaIlPrato {
    private Prato prato;
    private Robot robot = new Robot();

    private void tagliaPrato(){
        String[][] pratoPopolato = Prato.popolaPrato();
        Prato.stampaPrato(pratoPopolato);
    }



    public void setRobot(){

    }
}
