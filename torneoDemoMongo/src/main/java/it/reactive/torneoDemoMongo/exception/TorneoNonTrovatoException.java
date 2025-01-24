package it.reactive.torneoDemoMongo.exception;

public class TorneoNonTrovatoException extends CustomException {


    public TorneoNonTrovatoException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}
