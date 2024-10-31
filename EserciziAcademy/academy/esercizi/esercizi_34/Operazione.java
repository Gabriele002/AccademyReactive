package academy.esercizi.esercizi_34;

public class Operazione extends OggettoMatematico {
    private double op1;
    private double op2;
    private char op;

    public Operazione(double op1, double op2, char op) {
        this.op1 = op1;
        this.op2 = op2;
        this.op = op;
    }

    public double getOp1() {
        return op1;
    }

    public double getOp2() {
        return op2;
    }

    public char getOp() {
        return op;
    }

    @Override
    public double getValore() {
        switch (op) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            case '/':
                if (op2 != 0) {
                    return op1 / op2;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Operazione non valida: " + op);
        }
    }

    @Override
    public String stampa() {
        StringBuilder risultato = new StringBuilder();
        risultato.append(op1).append(" ").append(op).append(" ").append(op2).append(" = ").append(getValore());
        return risultato.toString();
    }
}

