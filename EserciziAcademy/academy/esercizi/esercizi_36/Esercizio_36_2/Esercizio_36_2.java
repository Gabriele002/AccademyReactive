package academy.esercizi.esercizi_36.Esercizio_36_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Esercizio_36_2 {
    public static void main(String[] args) {
        Esercizio_36_2 esercizio_36_2 = new Esercizio_36_2();
        esercizio_36_2.soluzione();
    }

    private void soluzione() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Inseriscimi il path di un file");
        String url = scn.next();

        try {
            FileInputStream input = new FileInputStream(url);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
