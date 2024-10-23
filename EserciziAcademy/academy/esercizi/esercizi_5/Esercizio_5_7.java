package academy.esercizi.esercizi_5;

public class Esercizio_5_7 {
        public static void main(String[] args) {
            String creditCardNumber ="4123-5678-9012-3450";
            String senzaTrattini = rimuoviTrattini(creditCardNumber);
            System.out.println("Stringa originale: " + creditCardNumber);
            System.out.println("Stringa senza trattini: " + senzaTrattini);
        }

        public static String rimuoviTrattini(String str) {
            StringBuilder stringaFormattata = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char carattere = str.charAt(i);
                if (carattere != '-') {
                    stringaFormattata.append(carattere);
                }
            }
            return stringaFormattata.toString();
        }
}

