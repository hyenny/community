package org.example.community.domain.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

  @OneToOne
  @JoinColumn(name = "role_id")
  private Role role;

  private Member(String name, String email, String password, String nickname, Role role) {
    this.id = null;
    this.name = name;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.role = role;
  }

  public static Member createUser(String name, String email, String password, String nickname) {
    return new Member(name, email, password, nickname, Role.create(RoleType.ROLE_USER));
  }

  public static Member createAdmin(String name, String email, String password, String nickname) {
    return new Member(name, email, password, nickname, Role.create(RoleType.ROLE_ADMIN));
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
