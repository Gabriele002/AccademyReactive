package it.reactive.torneoDemo.exception;

import it.reactive.torneoDemo.resource.ErrorResponse;
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

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleException(CustomException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getCod(), e.getDes());
        return ResponseEntity.status(550).body(errorResponse);
    }

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<Object> handleException(CustomException e) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("Codice errore", e.getCod());
//        body.put("Message", e.getMessage());
//        return ResponseEntity.status(550).body(body);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleExceptionConstrain(ConstraintViolationException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCod("C6");
        errorResponse.setDes("Errore di validazione");
        return ResponseEntity.status(550).body(errorResponse);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getDefaultMessage());
//        }
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("message", ex.getMessage());
//        body.put("errors", errors);
//        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
//    }
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse("C6","Errori di validazione");
//        return ResponseEntity.status(550).body(errorResponse);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        body.put("errors", errors);
        body.put("Cod: ", "C6");
        return ResponseEntity.status(550).body(body);
    }

}
