package org.example.community.domain.file.application;

import java.util.List;
import java.util.UUID;

public record GetAllUploadFilesQuery(
    List<UUID> ids,
    UUID createdBy
) {
}
