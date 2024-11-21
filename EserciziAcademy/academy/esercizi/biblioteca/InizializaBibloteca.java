package academy.esercizi.biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InizializaBibloteca {

    public Bibloteca bibloteca() throws IOException {
        Bibloteca bibloteca = new Bibloteca();

        List<String> listaDiLibriFile = Files.readAllLines(Paths.get("C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\biblioteca\\file\\libroFile"));
        for (String libro : listaDiLibriFile) {
            String[] attributi = libro.split(";");
            bibloteca.creaLibro(attributi[0], attributi[1]);
        }

        List<String> listaBiblotecaFile = Files.readAllLines(Paths.get("C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\biblioteca\\file\\biblotecario"));
        for (String biblotecario : listaBiblotecaFile) {
            String[] attributi = biblotecario.split(";");
            bibloteca.creaUtente(attributi[0], attributi[1], LivelliDiAccesso.Bibliotecario,true);
        }

        List<String> listaStudentiFile = Files.readAllLines(Paths.get("C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\biblioteca\\file\\studentiFile"));
        for (String listaStudenti : listaStudentiFile) {
            String[] attributi = listaStudenti.split(";");
            bibloteca.creaUtente(attributi[0], attributi[1], LivelliDiAccesso.Studente,true);
        }
        return bibloteca;
    }
}
