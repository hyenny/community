package org.example.community.domain.auth.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class MemberPrinciple implements UserDetails {
  private final transient Member member;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    var grantedAuthority = new SimpleGrantedAuthority(member.getRole().getName().name());
    return Collections.singleton(grantedAuthority);
  }

  @Override
  public String getPassword() {
    return member.getPassword();
  }

  @Override
  public String getUsername() {
    return member.getEmail();
  }

  public UUID getId() {
    return member.getId();
  }

  public String getNickname() {
    return member.getNickname();
  }
}
