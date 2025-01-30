package org.example.community.domain.file.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "upload_file")
@Entity
public class UploadFile {

  @Id
  @UuidGenerator(style = Style.TIME)
  private UUID id;

  @Column(nullable = false)
  private String originalFilename;

  @Column(nullable = false)
  private String storeFilename;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @Builder
  public UploadFile(String originalFilename, String storeFilename) {
    this.originalFilename = originalFilename;
    this.storeFilename = storeFilename;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
