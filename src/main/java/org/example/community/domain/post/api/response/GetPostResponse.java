package org.example.community.domain.post.api.response;

import java.time.LocalDateTime;
import java.util.List;
import org.example.community.domain.post.application.query.dto.AttachmentDetail;
import org.example.community.domain.post.application.query.dto.PostDetail;
import org.example.community.domain.reply.application.query.ReplyDetail;

public record GetPostResponse(
    String title,
    String content,
    String authorName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<AttachmentDetail> attachments,
    List<ReplyDetail> replies
) {
  public static GetPostResponse of(PostDetail detail) {
    return new GetPostResponse(
        detail.title(),
        detail.content(),
        detail.authorName(),
        detail.createdAt(),
        detail.updatedAt(),
        detail.attachments(),
        detail.replies()
    );
  }
}
