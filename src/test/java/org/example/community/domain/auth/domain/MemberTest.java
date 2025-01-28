package org.example.community.domain.auth.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MemberTest {

  @Test
  void createUser() {
    Member member = Member.create("김모모", "test1@test.com", "1234", "test1", Role.create(RoleType.ROLE_USER));
    assertThat(member.getRole().getName()).isEqualTo(RoleType.ROLE_USER);
  }

  @Test
  void createAdmin() {
    Member member = Member.create("임모모", "test2@test.com", "1234", "test2", Role.create(RoleType.ROLE_ADMIN));
    assertThat(member.getRole().getName()).isEqualTo(RoleType.ROLE_ADMIN);
  }
}