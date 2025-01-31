package org.example.community.domain.post.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, UUID> {

  @Query("SELECT p FROM Post p WHERE p.id = :id AND p.deletedAt IS NULL")
  Optional<Post> findActiveById(@Param("id") UUID id);
}
