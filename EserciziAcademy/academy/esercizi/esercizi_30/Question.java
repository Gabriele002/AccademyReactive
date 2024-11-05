package academy.esercizi.esercizi_30;

abstract class Question {
    protected String text;
    protected String answer;


    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public abstract boolean checkAnswer(String response);
    public abstract void display();
}

