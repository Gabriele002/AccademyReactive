package academy.esercizi.esercizi_30;

public class ChoiceQuestion extends Question {
    private final String[] scelte = new String[3];
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
    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(scelte[positionCorrect]);
    }

    @Override
    public void display() {
        for (int i = 0; i < scelte.length; i++) {
            System.out.println((i + 1) + ": " + scelte[i]);
        }
    }
}




