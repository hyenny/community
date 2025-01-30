package org.example.community.domain.post.application.command.dto;

import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;

public record DeletePostCommand(UUID id, MemberPrinciple currentMember) {

}
