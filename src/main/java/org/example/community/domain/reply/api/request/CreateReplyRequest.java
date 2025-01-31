package org.example.community.domain.reply.api.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.reply.application.command.CreateReplyCommand;
import org.example.community.domain.reply.domain.TargetType;

public record CreateReplyRequest(
    @NotBlank(message = "내용은 필수 값입니다.")
    String content
) {
  public CreateReplyCommand toCommand(TargetType targetType, UUID targetId, MemberPrinciple currentMember) {
    return new CreateReplyCommand(targetType, targetId, content, currentMember);
  }
}
