package org.example.community.domain.file.infra;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalFileStorage implements FileStorage {

  @Value("${file.upload-dir}")
  private String rootLocation;

  @PostConstruct
  public void init() {
    try {
      Path path = Paths.get(rootLocation);
      if (Files.notExists(path)) {
        Files.createDirectories(path);
      }
    }
    catch (IOException e) {
      throw new StorageException("초기화(디렉토리 생성)하는데 문제가 발생했습니다.", e);
    }
  }

  @Override
  public String store(MultipartFile file) {
    String originalFilename = file.getOriginalFilename();
    String storeFilename = createStoreFilename(originalFilename);

    try {
      Path target = Paths.get(getFullPath(storeFilename));
      Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
      return storeFilename;
    } catch (IOException e) {
      throw new StorageException("파일 저장에 실패했습니다", e);
    }
  }

  private String createStoreFilename(String path) {
    String ext = StringUtils.getFilenameExtension(path);
    String uuid = UUID.randomUUID().toString();
    return uuid + "." + ext;
  }

  private String getFullPath(String filename) {
    return rootLocation + filename;
  }

}
