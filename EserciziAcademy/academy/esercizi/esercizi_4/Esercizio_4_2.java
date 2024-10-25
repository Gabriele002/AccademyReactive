package academy.esercizi.esercizi_4;

public class Esercizio_4_2 {
    public static void main(String[] args) {
        Esercizio_4_2 esercizio_4_2 = new Esercizio_4_2();
        esercizio_4_2.soluzione();
    }

    public void soluzione(){
        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}
