package org.example.community.domain.post.api.response;

import java.time.LocalDateTime;
import org.example.community.domain.post.application.query.dto.PostDetail;
import org.example.community.domain.post.domain.Author;

public record GetPostResponse(
    String title,
    String content,
    Author author,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
  public static GetPostResponse of(PostDetail detail) {
    return new GetPostResponse(
        detail.title(),
        detail.content(),
        detail.author(),
        detail.createdAt(),
        detail.updatedAt()
    );
  }
}
