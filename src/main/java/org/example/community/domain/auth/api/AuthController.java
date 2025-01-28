package org.example.community.domain.auth.api;

import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.api.request.SignupRequest;
import org.example.community.domain.auth.application.command.AuthCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {
  private final AuthCommandService authCommandService;

  @PostMapping("/signup")
  public void signup(@RequestBody SignupRequest request) {
    authCommandService.signup(request.toCommand());
  }
}
