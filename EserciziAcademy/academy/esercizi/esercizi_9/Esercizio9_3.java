package academy.esercizi.esercizi_9;

import java.awt.*;

public class Esercizio9_3 {
    public static void main(String[] args) {
        Esercizio9_3 esercizio93 = new Esercizio9_3();
        esercizio93.risolvi();
    }

    private void risolvi (){
        int xA= 0, yA = 0;
        int xB= 4, yB = 0;
        int xC= 4, yC = 4;
        int xD= 0, yD = 4;

        String [][] figura = new String[5][5];
        figura[xA][yA] = "X";
        figura[xB][yB] = "X";
        figura[xC][yC] = "X";
        figura[xD][yD] = "X";


        for (int i = 0; i < figura.length; i++) {
            for (int j = 0; j < figura[i].length; j++) {
                if (figura[i][j] == null){
                    System.out.print("O");
                } else {
                    System.out.print(figura[i][j]);
                }
            }
            System.out.println("");
        }

        determinaFigura(xA,yA,xB,yB,xC,yC,xD,yD);

    }

    private static double distanza(double x1, double y1, double x2, double y2) {
       return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y1 - y2, 2));
    }

    private static void determinaFigura(double xA, double yA, double xB, double yB, double xC, double yC, double xD, double yD ){
        double latoAB = distanza(xA, yA, xB, yB);
        double latoBC = distanza(xB, yB, xC, yC);
        double latoCD = distanza(xC, yC, xD, yD);
        double latoDA = distanza(xA, yA, xD, yD);

        double diagonaleAC = distanza(xA, xC,yA,yB);
        double diagonaleBD = distanza(xB, xD,yB,yD);

        if (latoAB == latoBC && latoBC == latoCD && latoCD == latoDA){
            if (diagonaleAC == diagonaleBD){
                System.out.println("La figura e un quadrato");
            } else {
                System.out.println("La figura e un rombo");
            }
        } else if (latoAB == latoCD && latoBC == latoDA && diagonaleAC == diagonaleBD){
            System.out.println("La figura e un rettangolo");
        } else if (latoAB == latoCD || latoBC == latoDA) {
            System.out.println("La figura e un trapezio rettangolo");
        } else {
            System.out.println("La figura non e nessuna delle indicate");
        }


    }
}

