package org.example.community.domain.auth.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.community.domain.auth.application.command.LoginCommand;

public record LoginRequest(
    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    String password
) {
  public LoginCommand toCommand() {
    return new LoginCommand(email, password);
  }
}
