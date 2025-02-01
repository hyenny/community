package org.example.community.domain.auth.application.command;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.example.community.common.security.JwtTokenProvider;
import org.example.community.domain.auth.domain.Member;
import org.example.community.domain.auth.domain.MemberRepository;
import org.example.community.domain.auth.domain.RoleRepository;
import org.example.community.domain.auth.domain.RoleType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthCommandService {
  private final MemberRepository memberRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @Transactional
  public void signup(SignupCommand command) {
    var role = roleRepository.findByName(RoleType.ROLE_USER).orElseThrow(() -> new NoSuchElementException("해당 권한을 찾을 수 없습니다. : " + RoleType.ROLE_USER));
    var findMember = memberRepository.findByEmail(command.email());
    if (findMember.isPresent()) {
      throw new IllegalArgumentException("이미 등록된 이메일입니다. : " + command.email());
    }

    var user = Member.create(
        command.name(),
        command.email(),
        passwordEncoder.encode(command.password()),
        command.nickname(),
        List.of(role)
    );
    memberRepository.save(user);
  }

  @Transactional
  public String login(LoginCommand command) {
    UsernamePasswordAuthenticationToken authenticationToken =
        UsernamePasswordAuthenticationToken.unauthenticated(command.email(), command.password());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return jwtTokenProvider.createToken(authentication);
  }
}
