package it.reactive.carSharing.response;

import it.reactive.carSharing.dto.enumDto.EnumTipoUtente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Token {

    private long timeInmillis = System.currentTimeMillis();
    private String email;
    private EnumTipoUtente tipoUtente;

    public Token(String utente, EnumTipoUtente tipoUtente) {
        this.email = utente;
        this.tipoUtente = tipoUtente;
    }

    public Token(long timeInmillis, String utente, EnumTipoUtente tipoUtente) {
        this.timeInmillis = timeInmillis;
        this.email = utente;
        this.tipoUtente = tipoUtente;
    }

}
