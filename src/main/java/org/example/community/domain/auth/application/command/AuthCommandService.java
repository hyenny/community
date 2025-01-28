package org.example.community.domain.auth.application.command;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.domain.Member;
import org.example.community.domain.auth.domain.MemberRepository;
import org.example.community.domain.auth.domain.RoleRepository;
import org.example.community.domain.auth.domain.RoleType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthCommandService {
  private final MemberRepository memberRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signup(SignupCommand command) {
    var role = roleRepository.findByName(RoleType.ROLE_USER).orElseThrow(() -> new NoSuchElementException("해당 권한을 찾을 수 없습니다. : " + RoleType.ROLE_USER));
    var findMember = memberRepository.findByEmail(command.email());
    if (findMember.isPresent()) {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다. : " + command.email());
    }

    var user = Member.create(
        command.name(),
        command.email(),
        passwordEncoder.encode(command.password()),
        command.nickname(),
        role
    );
    memberRepository.save(user);
  }
}
