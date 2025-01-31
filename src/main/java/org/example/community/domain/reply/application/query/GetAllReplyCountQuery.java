package org.example.community.domain.reply.application.query;

import java.util.List;
import java.util.UUID;
import org.example.community.domain.reply.domain.TargetType;

public record GetAllReplyCountQuery(
    TargetType targetType,
    List<UUID> targetIds
) {

}
