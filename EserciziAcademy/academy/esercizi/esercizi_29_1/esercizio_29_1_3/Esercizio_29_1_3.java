package academy.esercizi.esercizi_29_1.esercizio_29_1_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Esercizio_29_1_3 {
    public static void main(String[] args) throws IOException {
        Esercizio_29_1_3 esercizio_29_1_3 = new Esercizio_29_1_3();
        esercizio_29_1_3.soluzione();
    }

    private void soluzione() throws IOException {
        String nomeFile = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizi_29_1\\esercizio_29_1_3\\ProvaTesto.text";
        List<String> testo = Files.readAllLines(Paths.get(nomeFile));
        Collections.reverse(testo);
        String nuovoFile = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizi_29_1\\esercizio_29_1_3\\ProvaTestoInverso.text";
        try {
            Path path = Paths.get(nuovoFile);
            Files.write(path, testo);
            System.out.println("File scritto con successo!");
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }
}
