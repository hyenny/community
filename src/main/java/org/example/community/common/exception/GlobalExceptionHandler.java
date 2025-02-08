package org.example.community.common.exception;

import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.example.community.domain.file.infra.StorageException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 유효하지 않은 아규먼트 에러
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.warn("handleMethodArgumentNotValidException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "유효하지 않은 입력값입니다.", e.getBindingResult());
    return ResponseEntity.badRequest().body(response);
  }

  /**
   * 타입 변환 불가 에러
   */
  @ExceptionHandler(TypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleTypeMismatchException(TypeMismatchException e) {
    log.warn("handleTypeMismatchException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "요청 값의 형식이 올바르지 않습니다.");
    return ResponseEntity.badRequest().body(response);
  }

  /**
   * 잘못된 요청 형식 에러
   */
  @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
  public ResponseEntity<ErrorResponse> handleIllegalsException(RuntimeException e) {
    log.warn("handleIllegalsException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  /**
   * 리소스를 찾지 못할 경우 에러
   */
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
    log.warn("handleNoSuchElementException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.NOT_FOUND.value(), e.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  /**
   * 인증 관련 에러
   */
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
    log.warn("handleAuthenticationException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.UNAUTHORIZED.value(), "잘못된 인증 정보입니다.");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  /**
   * 인가(권한) 관련 에러
   */
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
    log.warn("handleAccessDeniedException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.FORBIDDEN.value(), "접근 권한이 없습니다.");
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  }

  /**
   * 파일 저장소 관련 에러
   */
  @ExceptionHandler(StorageException.class)
  public ResponseEntity<ErrorResponse> handleStorageException(StorageException e) {
    log.error("handleStorageException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "파일 작업 중 오류가 발생했습니다.");
    return ResponseEntity.internalServerError().body(response);
  }

  /**
   * 그 외 에러
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    log.error("handleException", e);
    ErrorResponse response = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부 오류가 발생했습니다.");
    return ResponseEntity.internalServerError().body(response);
  }
}
