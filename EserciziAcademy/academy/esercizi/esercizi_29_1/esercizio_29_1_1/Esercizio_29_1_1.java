package academy.esercizi.esercizi_29_1.esercizio_29_1_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Esercizio_29_1_1 {
    public static void main(String[] args) {
        Esercizio_29_1_1 esercizio_29_1_1 = new Esercizio_29_1_1();
        esercizio_29_1_1.soluzione();
    }

    private void soluzione() {
        String url =("C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizi_29_1\\esercizio_29_1_1\\prova");

        List<String> righe = null;
        try {
            righe = Files.readAllLines(Paths.get(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (int i = righe.size() - 1; i >= 0; i--) {
            if (righe.get(i).isEmpty()) {
                righe.remove(righe.get(i));
            }
        }


        try {
            Files.write(Paths.get(url), righe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
