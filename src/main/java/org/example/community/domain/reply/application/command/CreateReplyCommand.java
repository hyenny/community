package org.example.community.domain.reply.application.command;

import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.reply.domain.TargetType;

public record CreateReplyCommand(TargetType targetType, UUID targetId, String content, MemberPrinciple currentMember) {

}
