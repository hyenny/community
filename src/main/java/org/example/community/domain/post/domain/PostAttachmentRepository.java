package org.example.community.domain.post.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostAttachmentRepository extends JpaRepository<PostAttachment, Long> {
  List<PostAttachment> findByPostId(UUID postId);

  @Query("SELECT new org.example.community.domain.post.domain.AttachmentCount(pa.post.id, COUNT(pa)) FROM PostAttachment pa WHERE pa.post.id IN :postIds AND pa.status = 'ACTIVE' GROUP BY pa.post.id")
  List<AttachmentCount> countAllByPostIds(@Param("postIds")List<UUID> postIds);

}
