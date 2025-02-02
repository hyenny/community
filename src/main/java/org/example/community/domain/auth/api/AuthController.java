package org.example.community.domain.auth.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.api.docs.AuthDocument;
import org.example.community.domain.auth.api.request.LoginRequest;
import org.example.community.domain.auth.api.request.SignupRequest;
import org.example.community.domain.auth.api.response.LoginResponse;
import org.example.community.domain.auth.application.command.AuthCommandService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController implements AuthDocument {
  private final AuthCommandService authCommandService;

  @Override
  @PostMapping("/signup")
  public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest request) {
    authCommandService.signup(request.toCommand());
    return ResponseEntity.ok().build();
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
    var token = authCommandService.login(request.toCommand());

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    return new ResponseEntity<>(new LoginResponse(token), httpHeaders, HttpStatus.OK);
  }
}
