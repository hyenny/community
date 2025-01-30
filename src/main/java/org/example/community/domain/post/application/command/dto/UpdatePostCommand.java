package org.example.community.domain.post.application.command.dto;

import java.util.List;
import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;

public record UpdatePostCommand(UUID id, String title, String content, List<UUID> fileIds, MemberPrinciple currentMember) {
}
