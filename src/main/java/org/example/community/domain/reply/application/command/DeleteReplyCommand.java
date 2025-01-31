package org.example.community.domain.reply.application.command;

import java.util.UUID;
import org.example.community.domain.reply.domain.TargetType;

public record DeleteReplyCommand(TargetType targetType, UUID targetId) {

}
