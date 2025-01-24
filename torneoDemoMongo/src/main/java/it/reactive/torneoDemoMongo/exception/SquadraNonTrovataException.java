package it.reactive.torneoDemoMongo.exception;


public class SquadraNonTrovataException extends CustomException {


    public SquadraNonTrovataException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}
