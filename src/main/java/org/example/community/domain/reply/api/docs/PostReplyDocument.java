package org.example.community.domain.reply.api.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.example.community.common.exception.ApiErrorResponses;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.reply.api.request.CreateReplyRequest;
import org.springframework.http.ResponseEntity;

@ApiErrorResponses
@Tag(name = "2. 게시판 답글 API", description = "Q&A 게시판. 관리자(ADMIN) 만 접근할 수 있습니다.")
public interface PostReplyDocument {

  @ApiResponse(responseCode = "200", description = "성공")
  @Operation(summary = "답글 등록")
  ResponseEntity<Void> create(
      @Parameter(name = "postId", description = "게시글 ID", in = ParameterIn.PATH) UUID postId,
      CreateReplyRequest request, MemberPrinciple currentMember
  );
}
