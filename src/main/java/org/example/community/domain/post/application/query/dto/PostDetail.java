package org.example.community.domain.post.application.query.dto;

import java.time.LocalDateTime;
import java.util.List;
import org.example.community.domain.post.domain.Author;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.reply.application.query.ReplyDetail;

public record PostDetail(
    String title,
    String content,
    Author author,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<AttachmentDetail> attachments,
    List<ReplyDetail> replies
) {
  public static PostDetail of(Post post, List<AttachmentDetail> attachments, List<ReplyDetail> replies) {
    return new PostDetail(
        post.getTitle(),
        post.getContent(),
        post.getAuthor(),
        post.getCreatedAt(),
        post.getUpdatedAt(),
        attachments,
        replies
    );
  }
}
