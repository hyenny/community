package org.example.community.domain.auth.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.community.domain.auth.application.command.SignupCommand;

public record SignupRequest(
    @NotBlank(message = "이름은 필수 값입니다.")
    String name,

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    String password,

    @NotBlank(message = "닉네임은 필수 값입니다.")
    String nickname
) {

  public SignupCommand toCommand() {
    return new SignupCommand(name, email, password, nickname);
  }
}
