package it.reactive.torneoDemo.exception;

public class TorneoNonTrovatoException extends CustomException {


    public TorneoNonTrovatoException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}
