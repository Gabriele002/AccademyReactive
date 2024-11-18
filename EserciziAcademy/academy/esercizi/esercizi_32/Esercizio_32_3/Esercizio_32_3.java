package academy.esercizi.esercizi_32.Esercizio_32_3;

import java.util.ArrayList;
import java.util.List;

public class Esercizio_32_3 {
    public static void main(String[] args) {
        Esercizio_32_3 esercizio_32_3 = new Esercizio_32_3();
        esercizio_32_3.soluzione();
    }

    private void soluzione() {
        List<Appuntamento> appuntamenti = new ArrayList<>();
        Giornaliero giornaliero = new Giornaliero("lavarsi i denti", 3);
        appuntamenti.add(giornaliero);
        Mensile mensile = new Mensile("pagara mutuo", 12);
        appuntamenti.add(mensile);
        Occasionale occasionale = new Occasionale("appuntamento dal dentista", 23, 01, 2025);
        appuntamenti.add(occasionale);

    }
}
