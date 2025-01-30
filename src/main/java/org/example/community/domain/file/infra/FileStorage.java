package org.example.community.domain.file.infra;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
  String store(MultipartFile file);
}
