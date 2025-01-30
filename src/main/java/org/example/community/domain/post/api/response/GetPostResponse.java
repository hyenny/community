package org.example.community.domain.post.api.response;

import java.time.LocalDateTime;
import java.util.List;
import org.example.community.domain.post.application.query.dto.AttachmentDetail;
import org.example.community.domain.post.application.query.dto.PostDetail;
import org.example.community.domain.post.domain.Author;

public record GetPostResponse(
    String title,
    String content,
    Author author,
    List<AttachmentDetail> attachments,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
  public static GetPostResponse of(PostDetail detail) {
    return new GetPostResponse(
        detail.title(),
        detail.content(),
        detail.author(),
        detail.attachments(),
        detail.createdAt(),
        detail.updatedAt()
    );
  }
}
