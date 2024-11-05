package academy.esercizi.esercizi_29;

public class Esercizio_29_1 {
    public static void main(String[] args) throws Exception {
        Esercizio_29_1 esercizio_29_1 = new Esercizio_29_1();
        esercizio_29_1.soluzione();

    }

    public void soluzione() throws Exception {
        String path = "C:\\Users\\G.Ciarleglio-cons\\Documents\\gitRoot\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\esercizi_29\\Prova.csv";
        CSVReader csvReader = new CSVReader(path);
        System.out.println("Il numero delle righe presenti nel file sono:");
        System.out.println(csvReader.numberOfRows());
        int riga = 2;
        System.out.println("Il numero delle colonne presenti nella riga " + riga);
        System.out.println(csvReader.numberOfFields(riga));
        int colonna = 3;
        System.out.println("Valore in riga " + riga + " colonna " + colonna);
        System.out.println(csvReader.field(riga, colonna));
    }
}

