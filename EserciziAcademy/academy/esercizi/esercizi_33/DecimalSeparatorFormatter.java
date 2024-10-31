package academy.esercizi.esercizi_33;

import java.text.NumberFormat;

public class DecimalSeparatorFormatter implements NumberFormatter {
    @Override
    public String format(int n) {
        NumberFormat formatter = NumberFormat.getInstance();
        return formatter.format(n);
    }
}

