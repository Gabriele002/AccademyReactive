package it.reactive.torneoDemo.utility;

public class Utility {

    public static String formattaStringaPerDb(String nomeSquadra) {
        if (nomeSquadra == null || nomeSquadra.isEmpty()) {
            return nomeSquadra;
        }
        return nomeSquadra.substring(0, 1).toUpperCase() + nomeSquadra.substring(1).toLowerCase();
    }
}
