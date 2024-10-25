package academy.esercizi.esercizi_2;

public class Esercizio_2_2 {

    public static void main(String[] args) {
        Esercizio_2_2 esercizio_2_2 = new Esercizio_2_2();
        esercizio_2_2.soluzione();
    }

    public void soluzione(){
        int n = 1;
        System.out.println(n + ", ");
        n++;
        System.out.println(n + ", ");
        n++;
        System.out.println(n + ", ");

        System.out.println("L errore potenziale potrebbe essere di scordarsi di incrementare la n creando cosi un loop infinito");
    }
}
