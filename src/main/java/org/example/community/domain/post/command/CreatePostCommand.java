package org.example.community.domain.post.command;

import org.example.community.domain.auth.domain.MemberPrinciple;

public record CreatePostCommand(String title, String content, MemberPrinciple currentMember) {

}
