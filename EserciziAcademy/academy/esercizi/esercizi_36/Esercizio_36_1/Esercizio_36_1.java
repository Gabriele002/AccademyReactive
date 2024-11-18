package academy.esercizi.esercizi_36.Esercizio_36_1;

import java.io.*;
import java.util.Scanner;

public class Esercizio_36_1 {
    public static void main(String[] args) {
        Esercizio_36_1 esercizio_36_1 = new Esercizio_36_1();
        esercizio_36_1.soluzione();
    }

    private void soluzione() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Inseriscimi il path di un file");
        String name = scn.next();
        try ( FileReader input = new FileReader(name);) {
            int i = input.read();
            while (i != -1){
                System.out.print((char)i);
                i = input.read();
            }
        } catch (IOException e) {
            System.out.println("Il file con il seguente path non e presente: " + e);
        }

    }
}
