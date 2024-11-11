package academy.esercizi.esercizi_36.esercizio_36_3;

public class Esercizio_36_3 {
    public static void main(String[] args) {
        Esercizio_36_3 esercizio_36_3 = new Esercizio_36_3();
        esercizio_36_3.soluzione();

    }

    private void soluzione() {
        ContoCorrente contoCorrente = new ContoCorrente(100, "123456789");
        contoCorrente.versa(2000);
        contoCorrente.versa(500);
        contoCorrente.preleva(200);
        System.out.println("------------");
        contoCorrente.preleva(900);
    }
}
