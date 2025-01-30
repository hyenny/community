package org.example.community.domain.file.infra;

import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
  String store(MultipartFile file);
  Path load(String filename);
  Resource loadAsResource(String filename);
}
