package org.example.community.domain.auth.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends CrudRepository<Member, UUID> {
  @Query("SELECT DISTINCT m FROM Member m JOIN FETCH m.roles r JOIN FETCH r.role WHERE m.email = :email")
  Optional<Member> findByEmailWithRoles(@Param("email") String email);

  Optional<Member> findByEmail(String email);
}
