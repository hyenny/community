package org.example.community.domain.reply.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "reply")
@Entity
public class Reply {
  @Id @UuidGenerator(style = Style.TIME)
  private UUID id;

  @Enumerated(EnumType.STRING)
  private TargetType targetType;

  @Column(nullable = false)
  private UUID targetId;

  @Column(nullable = false)
  private String content;

  @Embedded
  private Author author;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @Column
  private LocalDateTime deletedAt;

  @Builder
  public Reply(TargetType targetType, UUID targetId, String content, Author author) {
    this.targetType = targetType;
    this.targetId = targetId;
    this.content = content;
    this.author = author;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.deletedAt = null;
  }

  public void delete() {
    this.deletedAt = LocalDateTime.now();
  }
}
