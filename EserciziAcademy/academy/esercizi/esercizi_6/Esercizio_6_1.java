package academy.esercizi.esercizi_6;

public class Esercizio_6_1 {
    public static void main(String[] args) {
                System.out.println("------------------------");
                System.out.printf("%-4s %-4s %-4s %-4s%n", "x^1", "x^2", "x^3", "x^4");

        for (int i = 0; i < 10; i++) {
                System.out.printf("%-4d %-4d %-4d %-4d %n", i, (int)Math.pow(i,1), (int)Math.pow(i,2),(int)Math.pow(i,3),(int)Math.pow(i,4));
        }
                System.out.println("------------------------");
    }
}

