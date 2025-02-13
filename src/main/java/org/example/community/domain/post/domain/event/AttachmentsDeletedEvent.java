package org.example.community.domain.post.domain.event;

import java.util.List;
import java.util.UUID;

public record AttachmentsDeletedEvent(List<UUID> attachmentIds) { }
