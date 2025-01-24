package it.reactive.torneoDemoMongo.dto.resource;

public class ErrorResponse {

    private String cod;
    private String des;

    public ErrorResponse(String cod, String des) {
        this.cod = cod;
        this.des = des;
    }

    public ErrorResponse() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String codErr) {
        this.cod = codErr;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
