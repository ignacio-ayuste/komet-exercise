package com.unosof.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {

  private final HttpStatus status;
  private final String message;
  private final List<String> errors;

  @JsonCreator
  public ApiError(HttpStatus status, String message, List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public ApiError(HttpStatus status, String message, String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Collections.singletonList(error);
  }

  @Override
  public String toString() {
    return "ApiError{" + "status=" + status + ", message='" + message + '\'' + ", errors=" + errors
        + '}';
  }

}
