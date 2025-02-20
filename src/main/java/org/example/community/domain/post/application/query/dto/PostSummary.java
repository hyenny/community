package org.example.community.domain.post.application.query.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import org.example.community.domain.post.domain.Post;

public record PostSummary(
    UUID id,
    String title,
    String authorName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long attachmentCount,
    Long replyCount
) {
  public static PostSummary of(Post post, Long attachmentCount, Long replyCount) {
    return new PostSummary(
        post.getId(),
        post.getTitle(),
        post.getAuthor().name(),
        post.getCreatedAt(),
        post.getUpdatedAt(),
        attachmentCount,
        replyCount
    );
  }
}
