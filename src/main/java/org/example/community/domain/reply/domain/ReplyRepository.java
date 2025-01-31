package org.example.community.domain.reply.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  @Query("SELECT r FROM Reply r WHERE r.targetType = :targetType AND r.targetId = :targetId AND r.deletedAt IS NULL")
  List<Reply> findAllActiveByTarget(@Param("targetType") TargetType targetType, @Param("targetId") UUID targetId);

  @Query("SELECT new org.example.community.domain.reply.domain.ReplyCount(r.targetType, r.targetId, COUNT(r)) FROM Reply r WHERE r.targetType = :targetType AND r.targetId IN :targetIds AND r.deletedAt IS NULL GROUP BY r.targetId")
  List<ReplyCount> countAllByTarget(@Param("targetType") TargetType targetType, @Param("targetIds")List<UUID> targetIds);
}
