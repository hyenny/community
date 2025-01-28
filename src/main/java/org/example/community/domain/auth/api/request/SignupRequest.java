package org.example.community.domain.auth.api.request;

import org.example.community.domain.auth.application.command.SignupCommand;

public record SignupRequest(String name, String email, String password, String nickname) {

  public SignupCommand toCommand() {
    return new SignupCommand(name, email, password, nickname);
  }
}
