package it.reactive.carSharing.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum CodiceErrori {

    ERRORE_PSWNONVALIDA("Psw inserita non valida", HttpStatus.UNAUTHORIZED),
    ERRORE_UTENTENONTROVATO("Utente non presente registrarsi",HttpStatus.UNAUTHORIZED ),
    ERRORE_EMAILGIAVALIDA("Email gia registrata",HttpStatus.UNAUTHORIZED ),
    ERRORE_TOKENNULLO("Token nullo, fare il loggin",HttpStatus.UNAUTHORIZED ),
    ERRORE_UTENTENONVALIDO("User non valido per la seguente azione",HttpStatus.UNAUTHORIZED ),
    ERRORE_TOKEDIVERSODALLUTENTELOGGATO("User loggato non corrispondente",HttpStatus.INTERNAL_SERVER_ERROR ),
    ERRORE_TOKENSCADUTO("Token scaduto",HttpStatus.INTERNAL_SERVER_ERROR ),
    DATANONVALIDA("Data inserita non valida",HttpStatus.BAD_REQUEST );


    private final String desErrore;
    private final HttpStatus httpStatus;

    CodiceErrori(String desErrore, HttpStatus httpStatus) {
        this.desErrore = desErrore;
        this.httpStatus = httpStatus;
    }

}
