package academy.esercizi.esercizi_30;


public class MultipleChoiceQuestion extends Question {
    private String[] choices = new String[3];
    private int[] positionsCorrect = new int[3];
    private int correctCount = 0;

    public MultipleChoiceQuestion(String text) {
        this.text = text;
    }

    public void setChoice(String choiceText, int position, boolean isCorrect) {
        if (position >= 0 && position < choices.length) {
            choices[position] = choiceText;
            if (isCorrect && correctCount < positionsCorrect.length) {
                positionsCorrect[correctCount++] = position;
            }
        }
    }

    @Override
    public boolean checkAnswer(String response) {
        String[] risposte = response.split(",");
        boolean[] givenAnswers = new boolean[choices.length];

        for (String risposta : risposte) {
            int index = Integer.parseInt(risposta) - 1;
            if (index >= 0 && index < choices.length) {
                givenAnswers[index] = true;
            }
        }
        for (int i = 0; i < correctCount; i++) {
            if (!givenAnswers[positionsCorrect[i]]) {
                return false;
            }
        }
        for (int i = 0; i < givenAnswers.length; i++) {
            if (givenAnswers[i] && !isCorrect(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCorrect(int index) {
        for (int i = 0; i < correctCount; i++) {
            if (positionsCorrect[i] == index) {
                return true;
            }
        }
        return false;
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

