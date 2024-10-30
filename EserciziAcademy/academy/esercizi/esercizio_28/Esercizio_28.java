package academy.esercizi.esercizio_28;

import java.util.Scanner;

public class Esercizio_28 {
    private static final int RIGHE = 6;
    private static final int COLONNE = 7;
    private static final String VIVO = "o";
    private static final String MORTO = " ";

    public static void main(String[] args) {
        Esercizio_28 esercizio = new Esercizio_28();
        esercizio.avviaGioco();
    }

    public void avviaGioco() {
        String[][] tabella = new String[RIGHE][COLONNE];
        inizializzaTabella(tabella);
        popolaCella(tabella);
        simulaGenerazioni(tabella, 4);
    }

    private static void inizializzaTabella(String[][] tabella) {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                tabella[i][j] = MORTO;
            }
        }
    }

    private static void stampaTabella(String[][] tabella) {
        System.out.println("----------------");
        for (String[] riga : tabella) {
            System.out.print("|");
            for (String cella : riga) {
                System.out.print(cella);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("----------------");
        }
    }

    private static void popolaCella(String[][] tabella) {
        Scanner scanner = new Scanner(System.in);
        int riga;
        int colonna;
        boolean flag = true;

        do {
            System.out.print("Inserisci la riga della cella da popolare (o 100 per uscire): ");
            riga = scanner.nextInt() - 1;
            if (riga == 99) {
                flag = false;
            } else {
                System.out.print("Inserisci la colonna della cella da popolare: ");
                colonna = scanner.nextInt() - 1;
                if (isValidCoordinate(riga, colonna)) {
                    tabella[riga][colonna] = VIVO;
                    System.out.println("Cella [" + (riga + 1) + "][" + (colonna + 1) + "] popolata.");
                } else {
                    System.out.println("Coordinate non valide.");
                }
            }
        } while (flag);
    }


    private static void simulaGenerazioni(String[][] tabella, int numeroGenerazioni) {
        for (int i = 0; i < numeroGenerazioni; i++) {
            System.out.println("Generazione " + (i + 1) + ":");
            if (!ciSonoCelleVive(tabella)) {
                System.out.println("Non ci sono più celle vive");
                break;
            } else {
                stampaTabella(tabella);
                tabella = calcolaProssimaGenerazione(tabella);

            }
        }
    }

    private static String[][] calcolaProssimaGenerazione(String[][] tabella) {
        String[][] tabellaAggiornata = new String[RIGHE][COLONNE];
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                int viciniVivi = contaViciniVivi(tabella, i, j);
                if (tabella[i][j].equals(VIVO)) {
                    tabellaAggiornata[i][j] = (viciniVivi < 2 || viciniVivi > 3) ? MORTO : VIVO;
                } else {
                    tabellaAggiornata[i][j] = (viciniVivi == 3) ? VIVO : MORTO;
                }
            }
        }
        return tabellaAggiornata;
    }

    private static boolean ciSonoCelleVive(String[][] tabella) {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                if (tabella[i][j].equals(VIVO)) {
                    return true;
                }
            }
        }
        return false;
    }


    private static int contaViciniVivi(String[][] tabella, int riga, int colonna) {
        int count = 0;
        if (colonna - 1 >= 0) {
            if (tabella[riga][colonna - 1].equals(VIVO)) {
                count++;
            }
        }

        if (colonna + 1 < tabella[0].length) {
            if (tabella[riga][colonna + 1].equals(VIVO)) {
                count++;
            }
        }

        if (riga - 1 >= 0) {
            if (tabella[riga - 1][colonna].equals(VIVO)) {
                count++;
            }
        }

        if (riga + 1 < tabella.length) {
            if (tabella[riga + 1][colonna].equals(VIVO)) {
                count++;
            }
        }

        if (riga + 1 < tabella.length && colonna + 1 < tabella[0].length) {
            if (tabella[riga + 1][colonna + 1].equals(VIVO)) {
                count++;
            }
        }
        if (riga + 1 < tabella.length && colonna - 1 > 0) {
            if (tabella[riga + 1][colonna - 1].equals(VIVO)) {
                count++;
            }
        }

        if (colonna + 1 < tabella[0].length && riga - 1 > 0) {
            if (tabella[riga - 1][colonna + 1].equals(VIVO)) {
                count++;
            }
        }

        if (colonna - 1 > 0 && riga - 1 > 0) {
            if (tabella[riga - 1][colonna - 1].equals(VIVO)) {
                count++;
            }
        }
        return count;

    }

    private static boolean isValidCoordinate(int riga, int colonna) {
        return riga >= 0 && riga < RIGHE && colonna >= 0 && colonna < COLONNE;
    }
}




