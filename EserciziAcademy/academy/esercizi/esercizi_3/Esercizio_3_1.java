package academy.esercizi.esercizi_3;


public class Esercizio_3_1 {


    public static void main(String[] args) {
        int[][] tabellone = new int[9][10];
        popolaTabellone(tabellone);
        stampaTabellone(tabellone);

        trovaNumeriVicini(tabellone, 8, 8);
        trovaCoordinate(tabellone, 5);

    }

    public static void popolaTabellone(int[][] tabellone) {
        int numero = 1;
        for (int i = 0; i < tabellone.length; i++) {
            for (int j = 0; j < tabellone[i].length; j++) {
                if (numero <= 90) {//FIXME l'if-else è necessario?
                    tabellone[i][j] = numero;
                    numero++;
                } else {
                    tabellone[i][j] = 0;
                }
            }
        }
    }

    public static void stampaTabellone(int[][] tabellone) {
        for (int i = 0; i < tabellone.length; i++) {
            for (int j = 0; j < tabellone[i].length; j++) {
                if (tabellone[i][j] != 0) {
                    System.out.printf("%2d ", tabellone[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void trovaNumeriVicini(int[][] tabellone, int x, int y) {
        System.out.println("Numeri vicini: ");//FIXME aggiungere in stampa l'elemento che si trova nelle coordinate in input.

        //FIXME l'ordine degli elementi continua a non essere quello richiesto: sinistra, destra, alto, basso. Al momento sta stampando alto, basso, sinistra, destra
        //FIXME rendere chiaro in output la posizione dell'elemento che stai stampando (sinistra/destra/alto/basso)
        //FIXME gestire coordinate in input non presenti nel tabellone

        if (x - 1 >= 0){
            System.out.println( tabellone[x-1][y]);
        } else{
            System.out.println("x");//FIXME mettere una informazione parlante (es: "non presente")
        }

        if (x + 1 < tabellone.length){
            System.out.println(tabellone[x + 1][y]);
        } else{
            System.out.println("x");
        }
        if (y - 1 >= 0){
            System.out.println(tabellone[x][y - 1]);
        } else{
            System.out.println("x");
        }
        if (y + 1 < tabellone[0].length){
            System.out.println(tabellone[x][y + 1]);
        } else{
            System.out.println("x");
        }
    }

    public static void trovaCoordinate(int[][] tabellone,int numero) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < tabellone.length; i++) {
            for (int j = 0; j < tabellone[i].length;j++) {
                if (tabellone[i][j] == numero ){
                    x = i;
                    y = j;
                }
            }

        }
        //FIXME gestire un eventuale numero in input che non è presente
        trovaNumeriVicini(tabellone,x,y);
    }

    }
