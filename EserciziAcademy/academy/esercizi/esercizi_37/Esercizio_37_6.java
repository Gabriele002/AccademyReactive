package academy.esercizi.esercizi_37;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Esercizio_37_6 {
    public static void main(String[] args) {
        Esercizio_37_6 esercizio_37_6 = new Esercizio_37_6();
        esercizio_37_6.soluzione();
    }


//    Spesso si usano nomi di cartelle come dir1, dir2, e così via.
//    Quando ci sono dieci cartelle o più, il sistema operativo le visualizza secondo
//    l’ordine tipico di un dizionario, cioè dir1, dir10, dir11, dir12, dir2, dir3, e così via.
//    Una cosa fastidiosa a cui si può porre facilmente rimedio. Progettate un comparatore
//    che confronti stringhe che terminano con sequenze di cifre in modo da seguire il senso comune:
//    prima confrontate come stringa la parte che precede le cifre, poi confrontate il valore numerico delle cifre.

    private void soluzione() {
        String path = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi";
        File file = new File(path);
        List<String> listaCartelle = listFiles(file);

        List<String> listaFiltrata =
                listaCartelle.stream()
                        .map(fileName -> fileName.endsWith(".java") ? fileName.substring(0, fileName.length() - 5) : fileName)
                        .sorted(new ComparatorCartellePerNumero())
                        .collect(Collectors.toList());
        listaFiltrata.forEach(System.out::println);
    }

    public List<String> listFiles(File folder) {
        List<String> nomeCartelle = new ArrayList<>();
        File[] files = folder.listFiles();
        if (files != null) {
            for (File fileEntry : files) {
                if (fileEntry.isDirectory()) {
                    nomeCartelle.addAll(listFiles(fileEntry));
                } else if (fileEntry.getName().matches(".*\\d.*")) {
                    nomeCartelle.add(fileEntry.getName());
                }
            }
        }
        return nomeCartelle;
    }


    private static int estraiNumero(String nome) {
        String[] stringa = nome.split("_");
        for (String s : stringa) {
            if (s.matches("^[0-9]$|^[1-3][0-9]$")) {
                int interi = Integer.parseInt(stringa[stringa.length -1]);
                return interi;
            }
        }
        return 0;
    }

    public static class ComparatorCartellePerNumero implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String prefisso1 = o1.replaceAll("[^a-zA-Z]", "");
            String prefisso2 = o2.replaceAll("[^a-zA-Z]", "");

            int comparazionePrefisso = prefisso1.compareTo(prefisso2);
            if (comparazionePrefisso != 0) {
                return comparazionePrefisso;
            }

            String numberPart1 = o1.replaceAll("[^0-9]", "");
            String numberPart2 = o2.replaceAll("[^0-9]", "");

            int number1 = Integer.parseInt(numberPart1);
            int number2 = Integer.parseInt(numberPart2);

            return Integer.compare(number1, number2);
        }
    }
}


