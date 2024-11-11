package academy.esercizi.esercizi_30;

public class NumericQuestion extends Question<String,Integer> {
    public NumericQuestion() {
    }

    @Override
    public boolean checkAnswer(Integer response) {
        if (response == null){
            return false;
        }
        return answer.equals(response);
    }

    @Override
    public void display() {
        System.out.println(text);
    }
}
