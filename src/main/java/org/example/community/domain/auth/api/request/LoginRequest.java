package org.example.community.domain.auth.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.community.domain.auth.application.command.LoginCommand;

@Schema(description = "로그인 요청")
public record LoginRequest(

    @Schema(description = "이메일", example = "hello@test.com", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @Schema(description = "비밀번호", example = "hello1111", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호는 필수 값입니다.")
    String password
)  {
  public LoginCommand toCommand() {
    return new LoginCommand(email, password);
  }
}
