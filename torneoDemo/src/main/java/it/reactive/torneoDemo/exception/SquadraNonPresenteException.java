package it.reactive.torneoDemo.exception;

public class SquadraNonPresenteException extends CustomException {


    public SquadraNonPresenteException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}
