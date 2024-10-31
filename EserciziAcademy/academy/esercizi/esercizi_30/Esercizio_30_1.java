package academy.esercizi.esercizi_30;

public class Esercizio_30_1 {
    public static void main(String[] args) {
        Esercizio_30_1 esercizio_30_1 = new Esercizio_30_1();
        esercizio_30_1.soluzione();
    }

    public void soluzione() {
        Question question1 = new FillInQuestion("La capitale dell'Italia è ****, si trova nella regione **** ed ha **** abitanti", "La capitale dell'Italia è Roma, si trova nella regione Lazio ed ha 2800000 abitanti");
        question1.display();
        System.out.println(question1.checkAnswer("Roma,Lazio,2800000"));

        Question question2 = new NumericQuestion("Qual è la radice quadrata di 16?", "4");
        question2.display();
        System.out.println(question2.checkAnswer("4"));

        Question question3 = new FreeResponse("Qual è il colore del cielo?");
        question3.setAnswer("blu");
        question3.display();
        System.out.println(question3.checkAnswer("Blu"));

        ChoiceQuestion q  = new ChoiceQuestion();
        q.setAnswer("Daniele");
        q.setChoice("Daniele", 0,true);
        q.setChoice("Mario", 1,false);
        q.setChoice("Luigi", 2, false);
        System.out.println(q.display());
    }
}



