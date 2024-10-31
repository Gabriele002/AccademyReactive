package academy.esercizi.esercizi_30;

public class NumericQuestion extends Question {
    public NumericQuestion(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public boolean checkAnswer(String response) {
        try {
            Double.parseDouble(response);
            return response.equals(answer);
        } catch (NumberFormatException e) {
            System.out.println("Errore: La risposta deve essere un numero.");
            return false;
        }
    }

    @Override
    public boolean display() {
        System.out.println(text);
        return false;
    }
}
