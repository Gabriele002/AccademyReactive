package it.reactive.carSharing.service;

import it.reactive.carSharing.dto.enumDto.EnumTipoUtente;
import it.reactive.carSharing.exception.CodiceErrori;
import it.reactive.carSharing.exception.CustomException;
import it.reactive.carSharing.repository.AutistaRepo;
import it.reactive.carSharing.repository.PasseggeriRepo;
import it.reactive.carSharing.response.Token;
import it.reactive.carSharing.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${tokenDurata}")
    private long durataToken;

    @Autowired
    Utility utility;

    @Autowired
    AutistaRepo autistaRepo;

    @Autowired
    PasseggeriRepo passeggeriRepo;

    public String getToken(Token token) {
        LocalTime adesso = LocalTime.now();
        LocalTime oraSenzaNanosecondi = adesso.truncatedTo(ChronoUnit.SECONDS);
        return oraSenzaNanosecondi +
                " @ " +
                utility.codificaInBase64(token.getEmail()) +
                " @ " +
                utility.codificaInBase64(String.valueOf(token.getTipoUtente()));
    }

    public Token isTokenValid(String tokenString) {
        String[] tokenSplit = tokenString.split("@");
        if (tokenSplit.length != 3){
            throw new CustomException(CodiceErrori.ERRORE_TOKENNULLO);
        }

        String timeString = tokenSplit[0].trim();
        String utenteBase64 = tokenSplit[1].trim();
        String tipoUtenteBase64 = tokenSplit[2].trim();

        String utente = utility.decodeInBase64(utenteBase64);
        String tipoUtenteStr = utility.decodeInBase64(tipoUtenteBase64);
        EnumTipoUtente tipoUtente = EnumTipoUtente.valueOf(tipoUtenteStr);
        long timeInMillis = utility.convertToMilliseconds(timeString);

        if (!autistaRepo.findByeMail(utente).isPresent() && !passeggeriRepo.findByeMail(utente).isPresent()) {
            throw new CustomException(CodiceErrori.ERRORE_TOKEDIVERSODALLUTENTELOGGATO);
        }

        Token token = new Token(timeInMillis, utente, tipoUtente);
        long scadenzaToken = timeInMillis + durataToken;
        LocalTime adesso = LocalTime.now();
        long oraInMilli = adesso.toNanoOfDay() / 1000000;

        if (oraInMilli > scadenzaToken) {
            throw new CustomException(CodiceErrori.ERRORE_TOKENSCADUTO);
        }
        return token;
    }

}
