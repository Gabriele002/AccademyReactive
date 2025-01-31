package it.reactive.carSharing.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static it.reactive.carSharing.utility.Utility.formattaMillisecodi;

//@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getTimestamp(), e.getDes());
        return ResponseEntity.status(550).body(errorResponse);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleExceptionConstrain(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimeStamp(formattaMillisecodi(System.currentTimeMillis()));
        errorResponse.setDes("Errore di validazione");
        return ResponseEntity.status(550).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add("Campo: " + error.getField() + " - Errore: " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add("Errore globale: " + error.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(formattaMillisecodi(System.currentTimeMillis()), errors.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
