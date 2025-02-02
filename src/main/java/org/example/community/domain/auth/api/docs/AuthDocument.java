package org.example.community.domain.auth.api.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.community.common.exception.ApiErrorResponses;
import org.example.community.domain.auth.api.request.LoginRequest;
import org.example.community.domain.auth.api.request.SignupRequest;
import org.example.community.domain.auth.api.response.LoginResponse;
import org.springframework.http.ResponseEntity;

@ApiErrorResponses
@Tag(name = "0. 인증 API", description = "커뮤니티를 이용하려면 인증이 필요합니다.")
public interface AuthDocument {

  @ApiResponse(responseCode = "200", description = "성공")
  @Operation(summary = "회원가입")
  ResponseEntity<Void> signup(SignupRequest request);

  @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)))
  @Operation(summary = "로그인")
  ResponseEntity<LoginResponse> login(LoginRequest request);
}
