package academy.esercizi.esercizi_37;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Esercizio_37_7 {
    public static void main(String[] args) {
        Esercizio_37_7 esercizio_37_7 = new Esercizio_37_7();
        esercizio_37_7.soluzione();
    }

    private void soluzione() {
        String url = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizi_37\\ProvaTesto.text";
        HashMap<String, Integer> contatoreParole = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String riga;
            while ((riga = reader.readLine()) != null) {
                String[] parole = riga.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
                for (String parola : parole) {
                    if (contatoreParole.containsKey(parola)) {
                        contatoreParole.put(parola, contatoreParole.get(parola) + 1);
                    } else {
                        contatoreParole.put(parola, 1);
                    }

                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("%-15s %s%n", "Parola", "Numero di Parole");
        System.out.println();
        for (String parola : contatoreParole.keySet()) {
            System.out.printf("%-15s %d%n", parola, contatoreParole.get(parola));
        }

    }
}

