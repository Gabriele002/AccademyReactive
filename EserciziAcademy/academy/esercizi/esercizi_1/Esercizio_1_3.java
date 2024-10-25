package academy.esercizi.esercizi_1;

import java.util.Locale;

public class Esercizio_1_3 {
    public static void main(String[] args) {
        Esercizio_1_3 esercizio_1_3 = new Esercizio_1_3();
        esercizio_1_3.soluzione();
    }

    public void soluzione(){
        char[] letter = { 'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x',
                'y', 'z'};

         String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
                "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
                "-.--", "--.."};

         String nome = "Gabriele";

        System.out.printf("%-10s, %-10s %n", "Carattere", "Codice morse");
            for (int i = 0; i < nome.length(); i++) {
                for (int j = 0; j < letter.length; j++) {
                    if (nome.toLowerCase(Locale.ROOT).charAt(i) == (letter[j])) {
                        System.out.printf("%-10s %-10s %n", nome.charAt(i), morse[i]);
                        break;
                    }
                }
            }
        }
    }
