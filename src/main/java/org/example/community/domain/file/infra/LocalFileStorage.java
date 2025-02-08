package org.example.community.domain.file.infra;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
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

  @Override
  public Path load(String filename) {
    return Paths.get(rootLocation).resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      }
      else {
        throw new StorageException("파일을 읽을 수 없습니다 : " + filename);
      }
    }
    catch (MalformedURLException e) {
      throw new StorageException("파일을 읽을 수 없습니다 : " + filename, e);
    }
  }

  @Override
  public void delete(String filename) {
    try {
      Path file = load(filename);
      Files.deleteIfExists(file);
    } catch (Exception e) {
      throw new StorageException("파일 삭제가 실패했습니다.", e);
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
