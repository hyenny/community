package org.example.community.domain.post.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public record Attachment (
  @Column(name = "attachment_id", nullable = false)
  UUID id,
  @Column(name = "attachment_name", nullable = false)
  String name
) {
}
