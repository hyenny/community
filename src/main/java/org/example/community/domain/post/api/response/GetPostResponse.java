package org.example.community.domain.post.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import org.example.community.domain.post.application.query.dto.AttachmentDetail;
import org.example.community.domain.post.application.query.dto.PostDetail;
import org.example.community.domain.reply.application.query.ReplyDetail;

@Schema(description = "게시글 상세 조회 응답")
public record GetPostResponse(

    @Schema(description = "제목")
    String title,

    @Schema(description = "내용")
    String content,

    @Schema(description = "작성자", example = "김**")
    String authorName,

    @Schema(description = "생성일시")
    LocalDateTime createdAt,

    @Schema(description = "수정일시")
    LocalDateTime updatedAt,

    @Schema(description = "첨부파일")
    List<AttachmentDetail> attachments,

    @Schema(description = "답글")
    List<ReplyDetail> replies
) {
  public static GetPostResponse of(PostDetail detail) {
    return new GetPostResponse(
        detail.title(),
        detail.content(),
        detail.authorName(),
        detail.createdAt(),
        detail.updatedAt(),
        detail.attachments(),
        detail.replies()
    );
  }
}
