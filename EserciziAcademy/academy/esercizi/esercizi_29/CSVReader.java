package academy.esercizi.esercizi_29;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {
    private final String url;


    public CSVReader(String url) {
        this.url = url;
    }

    public int numberOfRows() throws IOException {
        return Files.readAllLines(Paths.get(url)).size();
    }

    public int numberOfFields(int riga) throws Exception {
        String contenutoRiga = Files.readAllLines(Paths.get(url)).get(riga - 1);
        String[] colonne = controlloColonna(contenutoRiga);
        return colonne.length;
    }


    public String field (int riga, int colonna) throws Exception {
        String contenutoRigaColonna = Files.readAllLines(Paths.get(url)).get(riga - 1);
        return controlloColonna(contenutoRigaColonna)[colonna - 1];
    }
    public String[] controlloColonna(String contenutoRiga) {
        return contenutoRiga.split(";");
    }

}
