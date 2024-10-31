package academy.esercizi.esercizi_33;

public class AccountingFormatter implements NumberFormatter {
    @Override
    public String format(int n) {
        if (n < 0) {
            return "(" + (-n) + ")";
        }
        return Integer.toString(n);
    }
}
