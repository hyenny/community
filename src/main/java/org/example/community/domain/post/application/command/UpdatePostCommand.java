package org.example.community.domain.post.application.command;

import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;

public record UpdatePostCommand(UUID id, String title, String content, MemberPrinciple currentMember) {
}
