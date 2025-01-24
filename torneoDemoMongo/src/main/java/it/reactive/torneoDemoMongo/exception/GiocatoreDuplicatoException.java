package it.reactive.torneoDemoMongo.exception;

public class GiocatoreDuplicatoException extends CustomException {

    public GiocatoreDuplicatoException(CodiceErrori codiceErrori) {
        super(codiceErrori);
    }
}

