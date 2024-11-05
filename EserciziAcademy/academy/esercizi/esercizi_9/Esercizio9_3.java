package academy.esercizi.esercizi_9;


public class Esercizio9_3 {
    public static void main(String[] args) {
        Esercizio9_3 esercizio93 = new Esercizio9_3();
        esercizio93.risolvi();
    }

    private void risolvi() {
        int xA= 2, yA = 2;
        int xB= 2, yB = 4;
        int xC= 4, yC = 2;
        int xD= 4, yD = 4;

        String[][] figura = new String[5][5];
        figura[yA][xA] = "X";
        figura[yB][xB] = "X";
        figura[yC][xC] = "X";
        figura[yD][xD] = "X";


        for (String[] strings : figura) {
            for (String string : strings) {
                if (string == null) {
                    System.out.print(".");
                } else {
                    System.out.print(string);
                }
            }
            System.out.println("");
        }

        determinaFigura(xA, yA, xB, yB, xC, yC, xD, yD);

    }

    private static double distanza(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

    }

    private static void determinaFigura(double xA, double yA, double xB, double yB, double xC, double yC, double xD, double yD) {
        double latoAB = distanza(xA, yA, xB, yB);
        double latoBC = distanza(xB, yB, xC, yC);
        double latoCD = distanza(xC, yC, xD, yD);
        double latoDA = distanza(xA, yA, xD, yD);

        double diagonaleAC = distanza(xA, yA, xC, yC);
        double diagonaleBD = distanza(xB, yB, xD, yD);

        if (latoAB == latoBC && latoBC == latoCD && latoCD == latoDA) {
            if (diagonaleAC == diagonaleBD) {
                System.out.println("La figura è un quadrato");
            } else {
                System.out.println("La figura è un rombo");
            }
        } else if (latoAB == latoCD && latoBC == latoDA && diagonaleAC == diagonaleBD) {
            System.out.println("La figura è un rettangolo");
        } else if ((latoAB == latoCD && latoDA == 0) || (latoBC == latoDA && latoAB == 0)) {
            System.out.println("La figura è un trapezio rettangolo");
        } else {
            System.out.println("La figura non è nessuna delle indicate");
        }
    }

}

