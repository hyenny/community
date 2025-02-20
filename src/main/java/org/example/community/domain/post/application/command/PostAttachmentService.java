package org.example.community.domain.post.application.command;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.file.application.GetAllUploadFilesQuery;
import org.example.community.domain.file.application.UploadFileService;
import org.example.community.domain.post.domain.Attachment;
import org.example.community.domain.post.domain.event.AttachmentsDeletedEvent;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.post.domain.PostAttachment;
import org.example.community.domain.post.domain.PostAttachmentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class PostAttachmentService {
  private final PostAttachmentRepository postAttachmentRepository;
  private final UploadFileService uploadFileService;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Transactional
  public void update(Post post, List<UUID> attachmentIds) {
    // 1. 기존 첨부 파일 목록을 조회
    var postAttachments = postAttachmentRepository.findByPostId(post.getId());

    // 2. 기존 첨부 파일 ID 목록
    var existingAttachmentIds = postAttachments.stream()
        .map(PostAttachment::getAttachmentId)
        .collect(Collectors.toSet());

    // 3. 삭제 대상 첨부 파일을 찾아 삭제
    for (PostAttachment postAttachment : postAttachments) {
      UUID attachmentId = postAttachment.getAttachmentId();
      if (!attachmentIds.contains(attachmentId)) {
        postAttachment.delete();
        postAttachmentRepository.save(postAttachment);
      }
    }

    // 4. 신규 첨부 파일 ID 목록을 찾아서 추가
    var newAttachmentIds = attachmentIds.stream()
        .filter(attachmentId -> !existingAttachmentIds.contains(attachmentId))
        .toList();

    if (!newAttachmentIds.isEmpty()) {
      var uploadFiles = uploadFileService.getAll(new GetAllUploadFilesQuery(newAttachmentIds, post.getAuthor().id()));
      var newPostAttachments = uploadFiles.stream()
          .map(uploadFile ->
              PostAttachment.builder()
                  .post(post)
                  .attachment(new Attachment(uploadFile.id(), uploadFile.originalFilename(), uploadFile.storeFilename()))
                  .build())
          .toList();
      postAttachmentRepository.saveAll(newPostAttachments);
    }
  }

  @Transactional
  public void create(Post post, List<UUID> attachmentIds) {
    var uploadFiles = uploadFileService.getAll(new GetAllUploadFilesQuery(attachmentIds, post.getAuthor().id()));
    var postAttachments = uploadFiles.stream()
        .map(uploadFile -> PostAttachment.builder()
            .post(post)
            .attachment(new Attachment(uploadFile.id(), uploadFile.originalFilename(), uploadFile.storeFilename()))
            .build())
        .toList();
    postAttachmentRepository.saveAll(postAttachments);
  }

  /**
   * 게시글 첨부파일을 삭제합니다.
   * 파일 스토리지에 있는 파일도 삭제됩니다.
   */
  @Transactional
  public void delete(Post post) {
    var postAttachments = postAttachmentRepository.findByPostId(post.getId());
    for (PostAttachment postAttachment : postAttachments) {
      postAttachment.delete();
    }

    var attachmentIds = postAttachments.stream().map(PostAttachment::getAttachmentId).toList();
    applicationEventPublisher.publishEvent(new AttachmentsDeletedEvent(attachmentIds));
  }
}