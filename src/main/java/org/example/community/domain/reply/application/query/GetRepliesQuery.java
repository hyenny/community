package org.example.community.domain.reply.application.query;

import java.util.UUID;
import org.example.community.domain.reply.domain.TargetType;

public record GetRepliesQuery(
    TargetType targetType,
    UUID targetId
) {

}
