package org.example.community.domain.post.application.query.dto;

import java.util.UUID;
import org.example.community.domain.post.domain.Attachment;

public record AttachmentDetail(
    UUID id,
    String filename,
    String fileUrl
) {
  public static AttachmentDetail of(Attachment attachment, String fileUrl) {
    return new AttachmentDetail(attachment.id(), attachment.originalFilename(), fileUrl);
  }
}
