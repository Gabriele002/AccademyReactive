package academy.esercizi.esercizi_30;

abstract class Question <T,A>{
    protected T text;
    protected A answer;


    public void setText(T text) {
        this.text = text;
    }

    public void setAnswer(A answer) {
        this.answer = answer;
    }

    public T getText() {
        return text;
    }

    public A getAnswer() {
        return answer;
    }

    public abstract boolean checkAnswer(A response);
    public abstract void display();
}

