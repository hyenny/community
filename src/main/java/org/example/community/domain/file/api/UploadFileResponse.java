package org.example.community.domain.file.api;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.UUID;

@Schema(description = "파일 업로드 응답")
public record UploadFileResponse(
    @Schema(description = "업로드 된 파일 ID", example = "7f000001-94bd-10c8-8194-bd016fd90002", requiredMode = RequiredMode.REQUIRED)
    UUID id
) {
}
