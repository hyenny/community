package org.example.community.domain.auth.domain;

import java.util.ArrayList;
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
    var authorities = new ArrayList<GrantedAuthority>();
    for (MemberRole memberRole : member.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(memberRole.role.getName().name()));
    }
    return Collections.unmodifiableCollection(authorities);
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
