package org.example.community.domain.post.application.query.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import org.example.community.domain.post.domain.Author;

public record PostSummary(
    UUID id,
    String title,
    Author author,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
