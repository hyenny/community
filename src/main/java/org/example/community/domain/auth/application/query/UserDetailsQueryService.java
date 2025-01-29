package org.example.community.domain.auth.application.query;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.domain.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsQueryService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + "을 찾을 수 없습니다."));
    var grantedAuthority = new SimpleGrantedAuthority(member.getRole().getName().name());

    return new User(member.getEmail(), member.getPassword(), Collections.singleton(grantedAuthority));
  }
}
