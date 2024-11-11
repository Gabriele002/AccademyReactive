package academy.esercizi.esercizi_18.esercizio_18_2;

public class Esercizio_18_2 {
    public static void main(String[] args) {
        Esercizio_18_2 esercizio_18_2 = new Esercizio_18_2();
        esercizio_18_2.soluzione();
    }

    private void soluzione() {
        Studente studente = new Studente("Gabriele");
        studente.addQuiz(10);
        studente.addQuiz(8);
        studente.addQuiz(4);
        System.out.println(studente);
        Studente studente1 = new Studente("Pippo");
        studente1.addQuiz(12);
        studente1.addQuiz(15);
        studente1.addQuiz(21);
        System.out.println(studente1);
    }
}
