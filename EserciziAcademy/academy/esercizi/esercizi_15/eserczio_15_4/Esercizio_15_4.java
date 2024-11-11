package academy.esercizi.esercizi_15.eserczio_15_4;

public class Esercizio_15_4 {
    public static void main(String[] args) {
        Esercizio_15_4 esercizio_15_4 = new Esercizio_15_4();
        esercizio_15_4.soluzione();
    }

    private void soluzione() {
        Circuito circuito = new Circuito();
        circuito.cambiaPrimoInterruttore();
        System.out.println(circuito);
        circuito.cambiaSecondoInterruttore();
        System.out.println(circuito);
        circuito.cambiaSecondoInterruttore();
        System.out.println(circuito);
        circuito.cambiaPrimoInterruttore();
        System.out.println(circuito);
        circuito.cambiaPrimoInterruttore();
        System.out.println(circuito);
    }
}
