package academy.esercizi.esercizi_3;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Arrays;

public class Esercizio_3_2 {


    public static void main(String[] args) {
        Esercizio_3_2 esercizio_3_2 = new Esercizio_3_2();
        esercizio_3_2.soluzione();

    }

    public void soluzione() {
        int[][] board = new int[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            System.out.println("");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
        }

    }
}
