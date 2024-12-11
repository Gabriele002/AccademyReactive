package it.reactive.torneoDemo.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

  private String cod;
  private String des;
  private HttpStatus httpStatus;

  public CustomException(CodiceErrori codiceErrori){
    this.cod = codiceErrori.getCodErr();
    this.des = codiceErrori.getDesErrore();
    this.httpStatus = codiceErrori.getHttpStatus();
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getCod() {
    return cod;
  }

  public void setCod(String cod) {
    this.cod = cod;
  }

  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }
}
