package academy.esercizi.esercizi_33;

public class Esercizio_33_1 {
    public static void main(String[] args) {
        Esercizio_33_1 esercizio_33_1 = new Esercizio_33_1();
        esercizio_33_1.soluzione();
    }

    public void soluzione() {

        int[] numbers = {1000, -2000, 3000000, 42};

        NumberFormatter.displayNumbers(numbers, new DefaultFormatter());

        NumberFormatter.displayNumbers(numbers, new DecimalSeparatorFormatter());

        NumberFormatter.displayNumbers(numbers, new AccountingFormatter());

        NumberFormatter.displayNumbers(numbers, new BaseFormatter(16));
    }
}

