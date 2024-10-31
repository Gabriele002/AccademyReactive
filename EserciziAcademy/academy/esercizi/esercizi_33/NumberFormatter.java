package academy.esercizi.esercizi_33;

public interface NumberFormatter {
    String format(int n);

    static void displayNumbers(int[] numbers, NumberFormatter formatter) {
        for (int number : numbers) {
            String formatted = formatter.format(number);
            System.out.printf("%12s%n", formatted);
        }
    }
}



