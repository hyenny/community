package org.example.community.domain.auth.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MemberTest {

  @Test
  void createUser() {
    Member member = Member.createUser("김모모", "test1@test.com", "1234", "test1");
    assertThat(member.getRole().getName()).isEqualTo(RoleType.ROLE_USER);
  }

  @Test
  void createAdmin() {
    Member member = Member.createAdmin("임모모", "test2@test.com", "1234", "test2");
    assertThat(member.getRole().getName()).isEqualTo(RoleType.ROLE_ADMIN);
  }
}