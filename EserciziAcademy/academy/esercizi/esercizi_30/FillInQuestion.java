package academy.esercizi.esercizi_30;

public class FillInQuestion extends Question {

    public FillInQuestion(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public boolean checkAnswer(String response) {
        String[] risposte = response.split(",");
        StringBuilder filledText = new StringBuilder(text);
        for (String risposta : risposte) {
            int index = filledText.indexOf("****");
            filledText.replace(index, index + 4, risposta);
        }
        return filledText.toString().equals(answer);
    }

    @Override
    public void display() {
        System.out.println(text);
    }
}
