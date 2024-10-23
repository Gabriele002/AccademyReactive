package academy.esercizi.esercizi_18;

import academy.esercizi.esercizi_18.esercizio_18_4.model.ContoCorrente;
import academy.esercizi.esercizi_18.esercizio_18_4.model.Utente;

import java.math.BigDecimal;

public class Esercizio_18_4 {
    public static void main(String[] args) {
        Utente utente = new Utente("Gabriele");
        ContoCorrente contoCorrente = new ContoCorrente(BigDecimal.valueOf(10000), utente);
        utente.setContoCorrente(contoCorrente);
        contoCorrente.preleva(BigDecimal.valueOf(100));

    }
}
