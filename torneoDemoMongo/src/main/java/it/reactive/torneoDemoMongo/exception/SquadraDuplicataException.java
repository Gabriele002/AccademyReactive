package it.reactive.torneoDemoMongo.exception;


public class SquadraDuplicataException extends CustomException {


    public SquadraDuplicataException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}
