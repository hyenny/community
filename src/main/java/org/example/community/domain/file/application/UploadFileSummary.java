package org.example.community.domain.file.application;

import java.util.UUID;
import org.example.community.domain.file.domain.UploadFile;

public record UploadFileSummary(UUID id, String originalFilename, String storeFilename) {
  public static UploadFileSummary of(UploadFile uploadFile) {
    return new UploadFileSummary(uploadFile.getId(), uploadFile.getOriginalFilename(), uploadFile.getStoreFilename());
  }
}
