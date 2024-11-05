package academy.esercizi.esercizi_30;


public class MultipleChoiceQuestion extends Question {
    private final String[] scelte = new String[3];
    private final int[] positionsCorrect = new int[3];
    private int correctCount = 0;

    public MultipleChoiceQuestion(String text) {
        this.text = text;
    }

    public void setChoice(String choiceText, int posizione, boolean isCorrect) {
        if (posizione >= 0 && posizione < scelte.length) {
            scelte[posizione] = choiceText;
            if (isCorrect && correctCount < positionsCorrect.length) {
                positionsCorrect[correctCount++] = posizione;
            }
        }
    }

    @Override
    public boolean checkAnswer(String response) {
        String[] risposte = response.split(",");
        boolean[] risposteDate = new boolean[scelte.length];

        for (String risposta : risposte) {
            int index = Integer.parseInt(risposta) - 1;
            if (index >= 0 && index < scelte.length) {
                risposteDate[index] = true;
            }
        }
        for (int i = 0; i < correctCount; i++) {
            if (!risposteDate[positionsCorrect[i]]) {
                return false;
            }
        }
        for (int i = 0; i < risposteDate.length; i++) {
            if (risposteDate[i] && !isCorrect(i)) {
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
    public void display() {
        System.out.println(text);
        for (int i = 0; i < scelte.length; i++) {
            System.out.println((i + 1) + ": " + scelte[i]);
        }
    }
}

