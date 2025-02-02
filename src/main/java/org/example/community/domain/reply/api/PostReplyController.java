package org.example.community.domain.reply.api;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.reply.api.docs.PostReplyDocument;
import org.example.community.domain.reply.api.request.CreateReplyRequest;
import org.example.community.domain.reply.application.command.ReplyCommandService;
import org.example.community.domain.reply.domain.TargetType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostReplyController implements PostReplyDocument {
  private final ReplyCommandService replyCommandService;

  @Override
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/{postId}/replies")
  public ResponseEntity<Void> create(@PathVariable UUID postId,
      @RequestBody CreateReplyRequest request,
      @AuthenticationPrincipal MemberPrinciple currentMember) {
    replyCommandService.create(request.toCommand(TargetType.POST, postId, currentMember));
    return ResponseEntity.ok().build();
  }
}
