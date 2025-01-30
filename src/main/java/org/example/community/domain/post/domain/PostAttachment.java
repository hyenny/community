package org.example.community.domain.post.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post_attachment")
@Entity
public class PostAttachment {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  @Embedded
  private Attachment attachment;

  @Enumerated(EnumType.STRING)
  private AttachmentStatus status;

  @Builder
  public PostAttachment(Post post, Attachment attachment) {
    this.post = post;
    this.attachment = attachment;
    this.status = AttachmentStatus.ACTIVE;
  }

  public UUID getAttachmentId() {
    return attachment.id();
  }

  public void delete() {
    this.status = AttachmentStatus.DELETED;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostAttachment postAttachment = (PostAttachment) o;
    return Objects.equals(id, postAttachment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
