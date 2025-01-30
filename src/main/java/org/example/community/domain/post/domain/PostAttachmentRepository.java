package org.example.community.domain.post.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostAttachmentRepository extends JpaRepository<PostAttachment, Long> {
  List<PostAttachment> findByPostId(UUID postId);
}
