package it.reactive.carSharing.utility;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Base64;

@Component
public class Utility {


    public boolean isValidPassword(String password) {
        if (!password.matches(".*[0-9].*") || !password.matches(".*[A-Z].*")
                || !password.matches(".*[a-z].*")
                || !password.matches(".*[.,!].*")) {
            return false;
        }
        if (!password.matches("[0-9A-Za-z.,!]+")) {
            return false;
        }
        String reversed = new StringBuilder(password).reverse().toString();
        return !password.equals(reversed);
    }

    public String codificaInBase64(String psw){
        return Base64.getEncoder().encodeToString(psw.getBytes());
    }

    public String decodeInBase64(String pswCodificata) {
        byte[] decodedBytes = Base64.getDecoder().decode(pswCodificata);
        return new String(decodedBytes);
    }


    public long convertToMilliseconds(String timeString) {
        String[] timeParts = timeString.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = Integer.parseInt(timeParts[2]);
        return (hours * 3600L + minutes * 60L + seconds) * 1000;
    }

    public static String formattaMillisecodi(long millisecodi){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(millisecodi);
    }

}
