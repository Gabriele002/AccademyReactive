package academy.esercizi.biblioteca;

public class Utility {

    public static boolean controllaStringheNonVuote(String... stringhe) {
        for (String str : stringhe) {
            if (!str.isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
