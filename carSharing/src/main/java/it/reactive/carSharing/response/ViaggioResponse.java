package it.reactive.carSharing.response;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ViaggioResponse {

    private String dataPartenza;
    private String dataArrivo;
    private Integer numeroPassegeri;
    private String cittaPartenza;
    private String cittaArrivo;
    private AutistaResponse autista;
}
