package academy.esercizi.esercizi_30;

public class ChoiceQuestion<A> extends Question<String,A> {
    protected final int numeroRisposte  = 3;
    private final String[] scelte = new String[numeroRisposte];
    private int positionCorrect;

    public void setChoice(String choiceText, int posizione, boolean isCorrect) {
        if (posizione >= 0 && posizione < scelte.length) {
            scelte[posizione] = choiceText;
            if (isCorrect) {
                positionCorrect = posizione;
            }
        }
    }

    @Override
    public boolean checkAnswer(A response) {
        return response.equals(scelte[positionCorrect]);
    }


    @Override
    public void display() {
        for (int i = 0; i < scelte.length; i++) {
            System.out.println((i + 1) + ": " + scelte[i]);
        }
    }

    public String[] getScelte(){
        return scelte;
    }
}




