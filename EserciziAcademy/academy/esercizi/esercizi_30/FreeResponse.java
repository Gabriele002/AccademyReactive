package academy.esercizi.esercizi_30;

public class FreeResponse extends Question {
    public FreeResponse(String text) {
        this.text = text;
    }

    @Override
    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(answer);
    }

    @Override
    public boolean display() {
        System.out.println(text);
        return false;
    }
}
