package org.example.community.domain.post.domain;

import java.util.UUID;

public record AttachmentCount(
    UUID postId,
    Long count
) {

}
