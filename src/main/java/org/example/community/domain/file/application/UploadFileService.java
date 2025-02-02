package org.example.community.domain.file.application;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.file.domain.CreatedBy;
import org.example.community.domain.file.domain.UploadFile;
import org.example.community.domain.file.domain.UploadFileRepository;
import org.example.community.domain.file.infra.LocalFileStorage;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class UploadFileService {
  private final LocalFileStorage localFileStorage;
  private final UploadFileRepository uploadFileRepository;

  public UUID save(MultipartFile file, MemberPrinciple currentMember) {
    var storeFilename = localFileStorage.store(file);
    var uploadFile = UploadFile.builder()
        .originalFilename(file.getOriginalFilename())
        .storeFilename(storeFilename)
        .createdBy(CreatedBy.of(currentMember))
        .build();

    return uploadFileRepository.save(uploadFile).getId();
  }

  public List<UploadFileSummary> getAll(GetAllUploadFilesQuery query) {
    var uploadFiles = uploadFileRepository.findAllById(query.ids());
    validateUploadFiles(uploadFiles, query);

    return uploadFiles.stream()
        .map(UploadFileSummary::of)
        .toList();
  }

  private void validateUploadFiles(List<UploadFile> actualFiles, GetAllUploadFilesQuery query) {
    if (actualFiles.size() != query.ids().size()) {
      throw new IllegalArgumentException("본인이 업로드하지 않은 파일이 존재합니다.");
    }

    if (actualFiles.stream().anyMatch(file -> !file.equalsCreatedBy(query.createdBy()))) {
      throw new IllegalArgumentException("본인이 업로드하지 않은 파일이 존재합니다.");
    }
  }

  public String getFileDownloadUrl(String filename) {
    return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/files/")
        .path(filename)
        .toUriString();
  }

  public Resource loadAsResource(String filename) {
    return localFileStorage.loadAsResource(filename);
  }
}
