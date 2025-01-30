package org.example.community.domain.file.application;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.file.domain.UploadFile;
import org.example.community.domain.file.domain.UploadFileRepository;
import org.example.community.domain.file.infra.LocalFileStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UploadFileService {
  private final LocalFileStorage localFileStorage;
  private final UploadFileRepository uploadFileRepository;

  public UUID save(MultipartFile file) {
    var storeFilename = localFileStorage.store(file);
    var uploadFile = UploadFile.builder()
        .originalFilename(file.getOriginalFilename())
        .storeFilename(storeFilename)
        .build();

    return uploadFileRepository.save(uploadFile).getId();
  }
}
