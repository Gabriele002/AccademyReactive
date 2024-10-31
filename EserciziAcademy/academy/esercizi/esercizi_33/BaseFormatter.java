package academy.esercizi.esercizi_33;

public class BaseFormatter implements NumberFormatter {
    private int base;

    public BaseFormatter(int base) {
        this.base = base;
    }

    @Override
    public String format(int n) {
        return Integer.toString(n, base);
    }
}

