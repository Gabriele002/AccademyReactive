package academy.esercizi.esercizi_37;

import java.io.File;

public class Esercizio_37_4 {
    public static void main(String[] args) {
        Esercizio_37_4 esercizio_37_4 = new Esercizio_37_4();
        esercizio_37_4.soluzione();
    }

    final String path = "C://";
    final String estensione = ".txt";

    private void soluzione() {
        File directory = new File(path);
        listFiles(directory);
    }

    public void listFiles(File folder) {
        File[] files = folder.listFiles();
        if (files != null){
            for (File fileEntry : files) {
                if (fileEntry.isDirectory()) {
                    listFiles(fileEntry);
                } else
                if (fileEntry.getName().endsWith(estensione)){
                    System.out.println(fileEntry.getName());
                }
            }
        }
    }
}

