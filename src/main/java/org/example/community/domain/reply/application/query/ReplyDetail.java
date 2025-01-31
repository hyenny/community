package org.example.community.domain.reply.application.query;

import java.time.LocalDateTime;
import java.util.UUID;
import org.example.community.domain.reply.domain.Author;
import org.example.community.domain.reply.domain.Reply;

public record ReplyDetail(
    UUID id,
    String content,
    Author author,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
  public static ReplyDetail of(Reply reply) {
    return new ReplyDetail(reply.getId(), reply.getContent(), reply.getAuthor(), reply.getCreatedAt(), reply.getUpdatedAt());
  }
}
