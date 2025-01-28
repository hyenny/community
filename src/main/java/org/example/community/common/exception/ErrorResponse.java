package org.example.community.common.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor
public class ErrorResponse {

  private int status;
  private String message;
  private List<FieldError> errors;

  private ErrorResponse(int status, String message) {
    this(status, message, new ArrayList<>());
  }

  private ErrorResponse(int status, String message, List<FieldError> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public static ErrorResponse of(int status, String message) {
    return new ErrorResponse(status, message);
  }

  public static ErrorResponse of(int status, String message, BindingResult bindingResult) {
    return new ErrorResponse(status, message, FieldError.of(bindingResult));
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class FieldError {
    private String field;
    private String reason;

    private FieldError(String field, String reason) {
      this.field = field;
      this.reason = reason;
    }

    public static List<FieldError> of(String field, String reason) {
      List<FieldError> fieldErrors = new ArrayList<>();
      fieldErrors.add(new FieldError(field, reason));
      return fieldErrors;
    }

    private static List<FieldError> of(BindingResult bindingResult) {
      List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(error -> new FieldError(
              error.getField(),
              error.getDefaultMessage()))
          .toList();
    }
  }
}
