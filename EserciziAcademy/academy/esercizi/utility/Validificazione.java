package academy.esercizi.utility;

import com.sun.nio.sctp.IllegalReceiveException;

import java.math.BigDecimal;

public class Validificazione {

    public static boolean validificaMinoreDiZero(BigDecimal saldoIniziale, String errore){
        try {
            if (saldoIniziale.compareTo(BigDecimal.ZERO) <= 0 ){
                throw new IllegalReceiveException(errore);
            }
        } catch (Exception e) {
            System.out.println("Errore di validazione:" + e.getMessage());
        }
        return true;
    }

    public static boolean validificaPrelievo(BigDecimal saldoIniziale, BigDecimal importoPrelevato){
        try{
            if (saldoIniziale.compareTo(importoPrelevato) <= 0 ){
                throw  new IllegalReceiveException("Non puoi effetuare il prelievo poiche il saldo inizale e minore dell importo");
            }
        } catch(Exception e){
                System.out.println("Errore di validazione:" + e.getMessage());
        }
        return true;
    }

    public static boolean validificaBoifico(BigDecimal saldoIniziale, BigDecimal importoPrelevato){
        try{
            if (saldoIniziale.compareTo(importoPrelevato) <= 0 ){
                throw  new IllegalReceiveException("Non puoi effetuare il bonifico poiche il saldo inizale e minore dell importo");
            }
        } catch(Exception e){
            System.out.println("Errore di validazione:" + e.getMessage());
        }
        return true;
        }


    public static boolean validificaIntMinoreDiZero(int anno, String errore){
        try {
            if (anno <= 0 ){
                throw new IllegalReceiveException(errore);
            }
        } catch (Exception e) {
            System.out.println("Errore di validazione:" + e.getMessage());
        }
        return true;
    }

    public static boolean validificaNumeroCompresoTraUnoECento(int numero){
        try{
            if (numero > 1 && numero <=100){
                throw new IllegalArgumentException("Il numero inserito deve essere compreso tra uno e cento");
            }
        } catch (Exception e){
            System.out.println("Errore di valedizione" + e.getMessage());
        }
        return true;
    }
}






