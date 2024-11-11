package academy.esercizi.esercizi_12.esercizio_12_1;

public class Prato {
    private static int x = 8;
    private static int y = 12;
    private static boolean isFalciato = false;


    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static String[][] popolaPrato() {
        String[][] pratoPopolato = new String[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                    pratoPopolato[i][j] = "|";
                } else {
                    pratoPopolato[i][j] = "■";
                }
            }
        }
        return pratoPopolato;
    }

}
