package org.example.community.domain.file.api.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.community.common.exception.ApiErrorResponses;
import org.example.community.domain.file.api.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@ApiErrorResponses
@Tag(name = "파일 업로드 API", description = "커뮤니티에서 파일을 업로드 시 사용합니다.")
public interface UploadFileDocument {

  @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UploadFileResponse.class)))
  @Operation(summary = "파일 업로드")
  ResponseEntity<UploadFileResponse> uploadFile(@Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) MultipartFile file);

  @Operation(summary = "파일 다운로드", hidden = true)
  ResponseEntity<Resource> downloadFile(@Parameter(in = ParameterIn.PATH) String filename);
}
