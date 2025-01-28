package org.example.community.domain.auth.domain;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
  Optional<Role> findByName(RoleType name);
}
