package academy.esercizi.esercizi_30;


import java.util.List;

public class MultipleChoiceQuestion extends ChoiceQuestion<List<String>> {
    private int[] positionCorrect = new int[numeroRisposte];
    private int posizioniCorretteIndex;

    public MultipleChoiceQuestion() {
        posizioniCorretteIndex = 0;
    }

    @Override
    public boolean checkAnswer(List<String> risposta) {
        for (int i = 0; i < risposta.size(); i++) {
            if ((risposta.size() != (posizioniCorretteIndex))) {
                return false;
            }
            if (!risposta.get(i).equals(this.getScelte()[positionCorrect[i]])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setChoice(String choice, int indice, boolean rispEsatta) {
        this.getScelte()[indice] = choice;
        if (rispEsatta) {
            positionCorrect[posizioniCorretteIndex] = indice;
            posizioniCorretteIndex++;
        }
    }
}

