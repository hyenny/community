package org.example.community.domain.post.api.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.example.community.common.exception.ApiErrorResponses;
import org.example.community.common.page.PageResponse;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.post.api.request.CreatePostRequest;
import org.example.community.domain.post.api.request.UpdatePostRequest;
import org.example.community.domain.post.api.response.GetPostResponse;
import org.example.community.domain.post.application.query.dto.PostSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@ApiErrorResponses
@Tag(name = "1. 게시판 API", description = "Q&A 게시판. 사용자(USER)만 접근할 수 있습니다.")
public interface PostDocument {

  @ApiResponse(responseCode = "200", description = "성공")
  @Operation(summary = "게시글 등록", description = "게시글 등록시 첨부파일은 파일 업로드 API 호출한 결과를 사용해야 합니다.")
  ResponseEntity<Void> create(CreatePostRequest request, MemberPrinciple currentMember);

  @ApiResponse(responseCode = "200", description = "성공")
  @Operation(summary = "게시글 수정")
  ResponseEntity<Void> update(
      @Parameter(name = "id", description = "게시글 ID", in = ParameterIn.PATH) UUID id,
      UpdatePostRequest request, MemberPrinciple currentMember
  );

  @ApiResponse(responseCode = "200", description = "성공")
  @Operation(summary = "게시글 삭제")
  ResponseEntity<Void> delete(
      @Parameter(name = "id", description = "게시글 ID", in = ParameterIn.PATH) UUID id,
      MemberPrinciple currentMember
  );

  @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetPostResponse.class)))
  @Operation(summary = "게시글 상세 조회")
  ResponseEntity<GetPostResponse> get(@Parameter(name = "id", description = "게시글 ID", in = ParameterIn.PATH) UUID id);

  @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class)))
  @Operation(summary = "게시글 목록 조회")
  ResponseEntity<PageResponse<PostSummary>> page(@Parameter(name = "pageable", hidden = true, in = ParameterIn.QUERY) Pageable pageable);
}
