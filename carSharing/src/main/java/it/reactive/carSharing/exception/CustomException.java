package it.reactive.carSharing.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


import static it.reactive.carSharing.utility.Utility.formattaMillisecodi;

@Getter
@Setter
public class CustomException extends RuntimeException {

  private String timestamp;
  private String des;
  private HttpStatus httpStatus;

  public CustomException(CodiceErrori codiceErrori){
    this.timestamp = formattaMillisecodi(System.currentTimeMillis());
    this.des = codiceErrori.getDesErrore();
    this.httpStatus = codiceErrori.getHttpStatus();
  }

}
