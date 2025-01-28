package it.reactive.torneoDemoMongo.exception;

import org.springframework.http.HttpStatus;

public enum CodiceErrori {

    ERRORE_SQUADRANONPRESENTE("C4", "Squadra non presente ", HttpStatus.resolve(550)),
    ERRORE_SQUADRANONTROVATA("C5", "Squadra non trovata", HttpStatus.resolve(550)),
    ERRORE_TORNERONONTROVATO("C2", "Torneo non trovato", HttpStatus.resolve(550)),
    ERRORE_SQUADRADUPLICATA("C1", "Squadra gia censita",HttpStatus.resolve(550)),
    ERRORE_GIOCATOREDUPLICATO("C3", "Giocatore gia presente", HttpStatus.resolve(550)),
    ERRORE_GIOCATORENONTROVATO("C3", "Giocatore gia presente", HttpStatus.resolve(550));


    private String codErr;
    private String desErrore;
    private HttpStatus httpStatus;

    CodiceErrori(String codErr, String desErrore, HttpStatus httpStatus) {
        this.codErr = codErr;
        this.desErrore = desErrore;
        this.httpStatus = httpStatus;
    }

    public String getCodErr() {
        return codErr;
    }

    public void setCodErr(String codErr) {
        this.codErr = codErr;
    }

    public String getDesErrore() {
        return desErrore;
    }

    public void setDesErrore(String desErrore) {
        this.desErrore = desErrore;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
