package org.example.community.domain.post.api.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.post.application.command.dto.UpdatePostCommand;

public record UpdatePostRequest(
    @NotBlank(message = "제목은 필수 값입니다.")
    String title,
    @NotBlank(message = "내용은 필수 값입니다.")
    String content,
    List<UUID> attachmentIds
) {
    public UpdatePostCommand toCommand(UUID id, MemberPrinciple currentMember) {
        return new UpdatePostCommand(id, title, content, attachmentIds, currentMember);
    }
}
