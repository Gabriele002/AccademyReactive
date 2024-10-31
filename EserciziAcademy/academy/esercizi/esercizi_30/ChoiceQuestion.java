package academy.esercizi.esercizi_30;

public class ChoiceQuestion extends Question {
    private String[] choices = new String[3];
    private int positionCorrect;

    public void setChoice(String choiceText, int position, boolean isCorrect) {
        if (position >= 0 && position < choices.length) {
            choices[position] = choiceText;
            if (isCorrect) {
                positionCorrect = position;
            }
        }
    }

    @Override
    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(choices[positionCorrect]);
    }

    @Override
    public boolean display() {
        System.out.println(text);
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ": " + choices[i]);
        }
        return false;
    }
}




