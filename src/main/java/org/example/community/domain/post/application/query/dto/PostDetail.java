package org.example.community.domain.post.application.query.dto;

import java.time.LocalDateTime;
import org.example.community.domain.post.domain.Author;

public record PostDetail(
    String title,
    String content,
    Author author,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
