package org.example.community.domain.post.api;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.community.common.page.PageResponse;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.post.api.request.UpdatePostRequest;
import org.example.community.domain.post.api.response.GetPostResponse;
import org.example.community.domain.post.application.command.PostCommandService;
import org.example.community.domain.post.api.request.CreatePostRequest;
import org.example.community.domain.post.application.command.dto.DeletePostCommand;
import org.example.community.domain.post.application.query.dto.GetPostQuery;
import org.example.community.domain.post.application.query.PostQueryService;
import org.example.community.domain.post.application.query.dto.PostSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {
  private final PostCommandService postCommandService;
  private final PostQueryService postQueryService;

  @PostMapping
  public ResponseEntity<Void> create(
      @Valid @RequestBody CreatePostRequest request,
      @AuthenticationPrincipal MemberPrinciple currentMember
  ) {
    postCommandService.create(request.toCommand(currentMember));
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
      @PathVariable UUID id,
      @Valid @RequestBody UpdatePostRequest request,
      @AuthenticationPrincipal MemberPrinciple currentMember
  ) {
    postCommandService.update(request.toCommand(id, currentMember));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id, @AuthenticationPrincipal MemberPrinciple currentMember) {
    postCommandService.delete(new DeletePostCommand(id, currentMember));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetPostResponse> get(@PathVariable UUID id) {
    var postDetail = postQueryService.getActive(new GetPostQuery(id));
    return ResponseEntity.ok(GetPostResponse.of(postDetail));
  }

  @GetMapping
  public ResponseEntity<PageResponse<PostSummary>> page(Pageable pageable) {
    return ResponseEntity.ok(postQueryService.page(pageable));
  }
}
