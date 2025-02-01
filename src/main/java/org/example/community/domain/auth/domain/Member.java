package org.example.community.domain.auth.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
@Entity
public class Member {

  @Id
  @UuidGenerator(style = Style.TIME)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String nickname;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MemberRole> roles = new ArrayList<>();

  private Member(String name, String email, String password, String nickname) {
    this.id = null;
    this.name = name;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
  }

  public void addRole(Role role) {
    MemberRole memberRole = new MemberRole(this, role);
    roles.add(memberRole);
  }

  public static Member create(String name, String email, String password, String nickname, List<Role> roles) {
    var member = new Member(name, email, password, nickname);
    for (Role role : roles) {
      member.addRole(role);
    }
    return member;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Member member = (Member) obj;
    return Objects.equals(this.id, member.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }
}
