package it.reactive.carSharing.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponse {

    private String timeStamp;
    private String des;

    public ErrorResponse(String timeStamp, String des) {
        this.timeStamp = timeStamp;
        this.des = des;
    }

    public ErrorResponse() {
    }

}
