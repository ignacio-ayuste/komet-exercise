package com.unosof.controller;

import com.unosof.exception.EntityNotFoundException;
import com.unosof.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<ApiError> handleEntityNotFound(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), "error occurred");

    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ApiError apiError =
        new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "error occurred");
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}
