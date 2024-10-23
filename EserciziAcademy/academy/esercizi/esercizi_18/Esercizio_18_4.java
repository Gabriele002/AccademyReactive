package academy.esercizi.esercizi_18;

import academy.esercizi.esercizi_18.esercizio_18_4.model.Conto;
import academy.esercizi.esercizi_18.esercizio_18_4.model.Utente;
import academy.esercizi.utility.Validificazione;

import javax.swing.tree.MutableTreeNode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Esercizio_18_4 {
    public static void main(String[] args) {
        Esercizio_18_4 esercizio_18_4 = new Esercizio_18_4();
        esercizio_18_4.soluzione();


    }

    public void soluzione(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il saldo iniziale del conto corrente: ");
        BigDecimal saldoCorrente = scanner.nextBigDecimal();
        Validificazione.validificaMinoreDiZero(saldoCorrente, "Non puo essere minore di zero");


        System.out.print("Inserisci il saldo iniziale del conto risparmio: ");
        BigDecimal saldoRisparmio = scanner.nextBigDecimal();
        Validificazione.validificaMinoreDiZero(saldoCorrente, "Non puo essere minore di zero");

        Utente utenteGabriele = new Utente("Gabriele");

        Conto contoCorrente = new Conto(saldoCorrente);
        Conto contoRisparmio = new Conto(saldoRisparmio);

        contoRisparmio.setUtente(utenteGabriele);
        contoCorrente.setUtente(utenteGabriele);


        boolean continua = true;
        while (continua) {
            System.out.println("\n--- Menu Bancario ---");
            System.out.println("1. Deposito sul conto corrente");
            System.out.println("2. Prelievo dal conto corrente");
            System.out.println("3. Bonifico dal conto corrente");
            System.out.println("4. Visualizza saldo conto corrente");
            System.out.println("5. Deposito sul conto risparmio");
            System.out.println("6. Prelievo dal conto risparmio");
            System.out.println("7. Bonifico dal conto risparmio");
            System.out.println("8. Visualizza saldo conto risparmio");
            System.out.println("9. Esci");
            System.out.print("Seleziona un'operazione (1-9): ");
            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci l'importo da depositare nel conto corrente: ");
                    BigDecimal depositoCorrente = scanner.nextBigDecimal();
                    contoCorrente.deposita(depositoCorrente);
                    break;

                case 2:
                    System.out.print("Inserisci l'importo da prelevare dal conto corrente: ");
                    BigDecimal prelievoCorrente = scanner.nextBigDecimal();
                    contoCorrente.preleva(prelievoCorrente);
                    break;

                case 3:
                    System.out.print("Inserisci l'importo da trasferire dal conto corrente: ");
                    BigDecimal bonificoCorrente = scanner.nextBigDecimal();
                    contoCorrente.bonifico(contoRisparmio, bonificoCorrente);
                    break;

                case 4:
                    System.out.printf("Saldo attuale conto corrente: %.2f%n", contoCorrente.getSaldoIniziale());
                    break;

                case 5:
                    System.out.print("Inserisci l'importo da depositare nel conto risparmio: ");
                    BigDecimal depositoRisparmio = scanner.nextBigDecimal();
                    contoRisparmio.deposita(depositoRisparmio);
                    break;

                case 6:
                    System.out.print("Inserisci l'importo da prelevare dal conto risparmio: ");
                    BigDecimal prelievoRisparmio = scanner.nextBigDecimal();
                    contoRisparmio.preleva(prelievoRisparmio);
                    break;

                case 7:
                    System.out.print("Inserisci l'importo da trasferire dal conto risparmio: ");
                    BigDecimal bonificoRisparmio = scanner.nextBigDecimal();
                    contoRisparmio.bonifico(contoCorrente, bonificoRisparmio);
                    break;

                case 8:
                    System.out.printf("Saldo attuale conto risparmio: %.2f%n", contoRisparmio.getSaldoIniziale());
                    break;

                case 9:
                    continua = false;
                    System.out.println("Uscita dal menu bancario.");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }

        scanner.close();
    }


}
