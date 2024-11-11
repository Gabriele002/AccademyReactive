package academy.esercizi.esercizi_30;

public class FreeResponse extends Question<String,String> {
    public FreeResponse() {
    }
    @Override
    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(answer);
    }

    @Override
    public void display() {
        System.out.println(text);
    }
}
