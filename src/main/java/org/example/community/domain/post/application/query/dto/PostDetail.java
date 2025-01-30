package org.example.community.domain.post.application.query.dto;

import java.time.LocalDateTime;
import java.util.List;
import org.example.community.domain.post.domain.Author;
import org.example.community.domain.post.domain.Post;

public record PostDetail(
    String title,
    String content,
    Author author,
    List<AttachmentDetail> attachments,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
  public static PostDetail of(Post post, List<AttachmentDetail> attachments) {
    return new PostDetail(
        post.getTitle(),
        post.getContent(),
        post.getAuthor(),
        attachments,
        post.getCreatedAt(),
        post.getUpdatedAt()
    );
  }
}
