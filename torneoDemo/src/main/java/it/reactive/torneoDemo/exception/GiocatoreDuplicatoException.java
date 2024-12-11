package it.reactive.torneoDemo.exception;

public class GiocatoreDuplicatoException extends CustomException {

    public GiocatoreDuplicatoException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}

