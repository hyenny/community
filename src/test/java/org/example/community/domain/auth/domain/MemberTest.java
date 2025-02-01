package org.example.community.domain.auth.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

  @DisplayName("ROLE_USER 권한 member 생성")
  @Test
  void createUser() {
    Member member = Member.create("김모모", "test1@test.com", "1234", "test1", List.of(Role.create(RoleType.ROLE_USER)));

    assertThat(member.getRoles()).hasSize(1);
    assertThat(member.getRoles()).extracting("role.name").contains(RoleType.ROLE_USER);
  }

  @DisplayName("기존 ROLE_USER member 에 ROLE_ADMIN 권한 추가")
  @Test
  void addAdminRole() {
    Member member = Member.create("임모모", "test2@test.com", "1234", "test2", List.of(Role.create(RoleType.ROLE_USER)));
    member.addRole(Role.create(RoleType.ROLE_ADMIN));

    assertThat(member.getRoles()).hasSize(2);
    assertThat(member.getRoles()).extracting("role.name").contains(RoleType.ROLE_USER, RoleType.ROLE_ADMIN);
  }
}