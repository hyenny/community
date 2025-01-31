package org.example.community.domain.reply.domain;

import java.util.UUID;

public record ReplyCount(
    TargetType targetType,
    UUID targetId,
    Long count
) {

}
