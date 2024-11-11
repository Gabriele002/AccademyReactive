package academy.esercizi.esercizi_38.esercizio_38_1;

import academy.esercizi.utility.RandomNumber;

import java.util.Optional;

public class ElementoCasuale {

    Optional<Integer> valore;
    protected final int NUMERO_MINIMO = 100;
    protected final int NUMERO_MASSIMO = 2000;

    public ElementoCasuale() {
        int numeroCasuale = RandomNumber.generaNumeroCasuale(1, 2);
        if (numeroCasuale == 1){
           valore = Optional.empty();
        }
        if (numeroCasuale != 1){
            valore = Optional.of(RandomNumber.generaNumeroCasuale(NUMERO_MINIMO, NUMERO_MASSIMO));
        }
    }

    public Optional<Integer> getValore() {
        return valore;
    }

    public void setValore(Optional<Integer> valore) {
        this.valore = valore;
    }
}



