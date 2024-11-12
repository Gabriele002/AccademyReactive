package academy.esercizi.esercizi_19.esercizio_19_1;

public class Esercizio_19_1 {
    public static void main(String[] args) {
        Esercizio_19_1 esercizio_19_1 = new Esercizio_19_1();
        esercizio_19_1.soluzione();
    }

    private void soluzione() {
        Minivan minivan = new Minivan();

        minivan.setMarcia(Marcia.P);
        System.out.println("Test con marcia in P e tutto spento:");
        System.out.println(minivan.verificaPortaSinistra());
        System.out.println(minivan.verificaPortaDestra());


        minivan.setInterruttoreDiSinistra(Interruttore.ACCESO);
        minivan.setManigliaEsternaDiDestra(Maniglia.APERTA);
        minivan.setInterruttoreBambini(Interruttore.SPENTO);
        minivan.setInterruttoreGenerale(Interruttore.SPENTO);
        System.out.println("-----------------------------------");
        System.out.println("Test con marcia in P e porta di sinistra dovrebbe aprirsi:");
        System.out.println(minivan.verificaPortaSinistra());
        System.out.println(minivan.verificaPortaDestra());
        System.out.println("-----------------------------------");
        minivan.setMarcia(Marcia.D);
        System.out.println("Test con marcia in D");
        System.out.println(minivan.verificaPortaSinistra());
        System.out.println(minivan.verificaPortaDestra());
        System.out.println("-----------------------------------");
        minivan.setMarcia(Marcia.P);
        minivan.setInterruttoreBambini(Interruttore.ACCESO);
        System.out.println("\nTest con marcia in P e blocco bambini");
        System.out.println(minivan.verificaPortaSinistra());
        System.out.println(minivan.verificaPortaDestra());
    }
}
