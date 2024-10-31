package academy.esercizi.esercizi_34;

public class Esercizio_34 {
    public static void main(String[] args) {
        Esercizio_34 esercizio_34 = new Esercizio_34();
        esercizio_34.soluzione();
    }

    public void soluzione() {
        Raddoppiabile intero = new Intero(12);
        intero.raddoppia();
        Operazione o = new Operazione(((OggettoMatematico) intero).getValore(),
                new Frazione(15, 3).getValore(), '+');

        System.out.println(Raddoppiabile.descrivi());
        try {
            System.out.println(intero.isDimezzabile());
        } catch (Exception e) {
            System.out.println("ERRORE");
        }

        System.out.println(o.stampa());

        Triplicabile secondo = new Intero(18);
        secondo.triplica();
        System.out.println(((OggettoMatematico) secondo).getValore());

        Frazione f = new Frazione(2, 20);
        System.out.println(f.inversa().getValore());
        System.out.println(f.isDimezzabile());

    }
}


