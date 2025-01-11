package it.reactive.torneoDemo.utility;

public class Utility {

    public static String formattaStringaPerDb(String stringa) {
        return stringa.substring(0, 1).toUpperCase() + stringa.substring(1).toLowerCase();
    }
}
