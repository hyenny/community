package org.example.community.domain.auth.domain;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, UUID> {
}
