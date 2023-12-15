package com.chetouani.gc.advice;

import com.chetouani.gc.dto.response.ErrorResponse;
import com.chetouani.gc.exception.EntityNotFoundException;
import com.chetouani.gc.exception.IntegrityViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(100, e.getMessage()));
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(102, "This entity already exist"));
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(103, e.getMessage()));
    }

    @ExceptionHandler({IntegrityViolationException.class})
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(IntegrityViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(104, e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return new ResponseEntity<>(new ErrorResponse(101, errors.toString()), headers, status);
    }
}