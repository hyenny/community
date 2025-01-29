package org.example.community.domain.post.application.command;

import org.example.community.domain.auth.domain.MemberPrinciple;

public record CreatePostCommand(String title, String content, MemberPrinciple currentMember) {

}
