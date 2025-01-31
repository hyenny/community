package org.example.community.domain.post.application.query;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.file.application.UploadFileService;
import org.example.community.domain.post.application.query.dto.AttachmentDetail;
import org.example.community.domain.post.domain.AttachmentStatus;
import org.example.community.domain.post.domain.PostAttachment;
import org.example.community.domain.post.domain.PostAttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostAttachmentQueryService {
  private final PostAttachmentRepository postAttachmentRepository;
  private final UploadFileService uploadFileService;

  public List<AttachmentDetail> getAllActive(UUID postId) {
    return postAttachmentRepository.findByPostId(postId).stream()
        .filter(postAttachment -> postAttachment.getStatus().equals(AttachmentStatus.ACTIVE))
        .map(this::toAttachmentDetail)
        .toList();
  }

  private AttachmentDetail toAttachmentDetail(PostAttachment postAttachment) {
    var attachment = postAttachment.getAttachment();
    String downloadUrl = uploadFileService.getFileDownloadUrl(attachment.storeFilename());
    return new AttachmentDetail(attachment.id(), attachment.originalFilename(), downloadUrl);
  }

}
