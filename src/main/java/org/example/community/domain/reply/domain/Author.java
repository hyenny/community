package org.example.community.domain.reply.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public record Author(
    @Column(name = "author_id", nullable = false)
    UUID id,
    @Column(name = "author_name", nullable = false)
    String name
) {
}
