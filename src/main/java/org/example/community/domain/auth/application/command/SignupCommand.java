package org.example.community.domain.auth.application.command;

public record SignupCommand(String name, String email, String password, String nickname) {
}
