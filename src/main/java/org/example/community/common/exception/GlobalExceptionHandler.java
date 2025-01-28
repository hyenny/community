package org.example.community.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    log.error("handleException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    return ResponseEntity.internalServerError().body(response);
  }
}
