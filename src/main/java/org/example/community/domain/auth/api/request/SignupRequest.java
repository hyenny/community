package org.example.community.domain.auth.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.community.domain.auth.application.command.SignupCommand;

@Schema(description = "회원가입 요청")
public record SignupRequest(

    @Schema(description = "이름", example = "hello", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "이름은 필수 값입니다.")
    String name,

    @Schema(description = "이메일", example = "hello@test.com", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @Schema(description = "비밀번호", example = "hello1111", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호는 필수 값입니다.")
    String password,

    @Schema(description = "닉네임", example = "hello", minLength = 2, requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "닉네임은 필수 값입니다.")
    @Min(value = 2, message = "최소 2글자 이상 입력해야 합니다.")
    String nickname
) {

  public SignupCommand toCommand() {
    return new SignupCommand(name, email, password, nickname);
  }
}
