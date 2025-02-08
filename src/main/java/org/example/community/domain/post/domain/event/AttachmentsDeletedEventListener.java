package org.example.community.domain.post.domain.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.community.domain.file.application.UploadFileService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class AttachmentsDeletedEventListener {

    private final UploadFileService uploadFileService;

    @Async
    @TransactionalEventListener
    public void handle(AttachmentsDeletedEvent event) {
        log.info("AttachmentsDeletedEvent : {}", event.attachmentIds());
        uploadFileService.deleteAll(event.attachmentIds());
    }
}
