package com.grocery.mgmt.platform.common.exception;

import com.grocery.mgmt.platform.common.representation.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalResponseExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
        log.error("DataNotFoundException: " , ex);
        var response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParameterMissingException.class)
    public final ResponseEntity<Object> handleParameterMissingException(DataNotFoundException ex, WebRequest request) {
        log.error("ParameterMissingException: " , ex);
        var response = new Response(HttpStatus.OK.value(), ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(DataCreationFailedException.class)
    public final ResponseEntity<Object> handleDataCreationFailedException(DataCreationFailedException ex, WebRequest request) {
        log.error("DataCreationFailedException: " , ex);
        var response = new Response(HttpStatus.OK.value(), ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(DataUpdationFailedException.class)
    public final ResponseEntity<Object> handleDataUpdationFailedException(DataUpdationFailedException ex, WebRequest request) {
        log.error("DataUpdationFailedException: " , ex);
        var response = new Response(HttpStatus.OK.value(), ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidAccessException.class)
    public final ResponseEntity<Object> handleInvalidAccessException(InvalidAccessException ex, WebRequest request) {
        log.error("InvalidAccessException: " , ex);
        var response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParseJsonFailedException.class)
    public final ResponseEntity<Object> handleParseJsonFailedException(ParseJsonFailedException ex, WebRequest request) {
        log.error("ParseJsonFailedException: " , ex);
        var response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public final ResponseEntity<Object> handleDuplicateDataException(DuplicateDataException ex, WebRequest request) {
        log.error("DuplicateDataException: " , ex);
        var response = new Response(HttpStatus.CONFLICT.value(), ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
