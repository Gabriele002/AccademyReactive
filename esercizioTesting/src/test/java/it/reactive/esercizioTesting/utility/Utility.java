package it.reactive.esercizioTesting.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Utility {

    protected final String OGGETTO_PROPOSTO = "Vinile";

    private final Path FILE_PARTECIPANTI = Paths.get("C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\esercizioTesting\\src\\test\\resources\\setUpBasePartecipanti");
    protected List<String> partecipanti = setPartecipantiDaFile();

    private List<String> setPartecipantiDaFile() {
        List<String> partecipanti = null;
        try {
            partecipanti = Files.readAllLines(FILE_PARTECIPANTI);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return partecipanti;
    }
}
