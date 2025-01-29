package org.example.community.domain.post.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.post.command.PostCommandService;
import org.example.community.domain.post.api.request.CreatePostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {
  private final PostCommandService postCommandService;

  @PostMapping
  public ResponseEntity<Void> create(
      @Valid @RequestBody CreatePostRequest request,
      @AuthenticationPrincipal MemberPrinciple currentMember
  ) {
    postCommandService.create(request.toCommand(currentMember));
    return ResponseEntity.ok().build();
  }
}
