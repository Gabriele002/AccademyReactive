package it.reactive.torneoDemoMongo.exception;

public class SquadraNonPresenteException extends CustomException {


    public SquadraNonPresenteException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}
