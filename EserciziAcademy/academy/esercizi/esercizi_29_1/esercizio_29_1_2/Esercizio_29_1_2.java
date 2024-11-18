package academy.esercizi.esercizi_29_1.esercizio_29_1_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Esercizio_29_1_2 {
    public static void main(String[] args) throws IOException {
        Esercizio_29_1_2 esercizio_29_1_2 = new Esercizio_29_1_2();
        esercizio_29_1_2.soluzione();
    }

    private void soluzione() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il nome del file: ");
        String nomeFile = scanner.nextLine();


        List<String> testo = Files.readAllLines(Paths.get(nomeFile));

        int numeroCaratteri = 0;
        int numeroParole = 0;
        int numeroRighe = 0;


        for (String riga : testo) {
            numeroRighe++;
            numeroCaratteri += riga.length();
            numeroParole += riga.split(" ").length;
            ;
        }

        System.out.println("Caratteri totali = " + numeroCaratteri);
        System.out.println("Righe totali = " + numeroRighe);
        System.out.println("Numero di parole = " + numeroParole);
    }
}





