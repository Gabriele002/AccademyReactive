package academy.esercizi.esercizi_34;

public interface Raddoppiabile {
    void raddoppia();

    default boolean isDimezzabile() {
        throw new RuntimeException("NON SONO ANCORA STATO IMPLEMENTATO");
    }

    String DESCRIZIONE_CLASSE = "Questa interfaccia raddoppia il valore di un oggetto Raddoppiabile";

    static String descrivi() {
        return DESCRIZIONE_CLASSE;
    }
}

