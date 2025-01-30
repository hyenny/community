package org.example.community.domain.post.application.command.dto;

import java.util.List;
import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;

public record CreatePostCommand(String title, String content, List<UUID> attachmentIds, MemberPrinciple currentMember) {

}
