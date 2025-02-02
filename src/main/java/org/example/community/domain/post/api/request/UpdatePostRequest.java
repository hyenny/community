package org.example.community.domain.post.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.post.application.command.dto.UpdatePostCommand;

@Schema(description = "게시글 수정 요청")
public record UpdatePostRequest(

    @Schema(description = "제목", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "제목은 필수 값입니다.")
    String title,

    @Schema(description = "내용", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "내용은 필수 값입니다.")
    String content,

    @Schema(description = "첨부파일")
    List<UUID> attachmentIds
) {
    public UpdatePostCommand toCommand(UUID id, MemberPrinciple currentMember) {
        return new UpdatePostCommand(id, title, content, attachmentIds, currentMember);
    }
}
