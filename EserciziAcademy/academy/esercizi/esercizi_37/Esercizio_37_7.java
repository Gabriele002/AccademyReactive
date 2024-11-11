package academy.esercizi.esercizi_37;

import java.io.*;
import java.util.*;


public class Esercizio_37_7 {
    public static void main(String[] args) {
        Esercizio_37_7 esercizio_37_7 = new Esercizio_37_7();
        esercizio_37_7.letturaFileJava();
    }

    private void letturaFileDiTesto() {
        String url = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizi_37\\ProvaTesto.text";
        TreeMap<String, Integer> contatoreParole = new TreeMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String riga;
            while ((riga = reader.readLine()) != null) {
                String[] parole = riga.toLowerCase().split("[^A-Za-z0-9_]+");
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


    public void letturaFileJava()  {
        String url = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizi_37\\Esercizio_37_7.java";
        Map<String, List<Integer>> mappa = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(url))) {
            int numeroRiga = 1;

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] parole = linea.replaceAll("[^A-Za-z0-9_]+", " ").toLowerCase().split(" ");
                for (String parola : parole) {
                    if (!parola.isEmpty()) {
                        if (mappa.containsKey(parola)) {
                            mappa.get(parola).add(numeroRiga);
                        } else {
                            List<Integer> listaRighe = new ArrayList<>();
                            listaRighe.add(numeroRiga);
                            mappa.put(parola, listaRighe);
                        }
                    }
                }
                numeroRiga++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Errore nel trovare il file: " + e.getMessage());
        }

        System.out.printf("%-25s %-5s %n", "Parola", "Righe in cui appare");
        for (Map.Entry<String, List<Integer>> mappaCompleta : mappa.entrySet()) {
            System.out.printf("%-25s", mappaCompleta.getKey());
            for (Integer righe : mappaCompleta.getValue()) {
                System.out.printf("%-5d", righe);
            }
            System.out.println();
        }
    }
}

