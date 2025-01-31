package org.example.community.domain.post.application.query;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.example.community.common.page.PageResponse;
import org.example.community.domain.post.application.query.dto.GetPostQuery;
import org.example.community.domain.post.application.query.dto.PostDetail;
import org.example.community.domain.post.application.query.dto.PostSummary;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.post.domain.PostRepository;
import org.example.community.domain.reply.application.query.GetRepliesQuery;
import org.example.community.domain.reply.application.query.ReplyQueryService;
import org.example.community.domain.reply.domain.TargetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostQueryService {
  private final PostRepository postRepository;
  private final PostAttachmentQueryService postAttachmentQueryService;
  private final ReplyQueryService replyQueryService;

  public PostDetail getActive(GetPostQuery query) {
    var postId = query.id();
    var post = postRepository.findActiveById(postId).orElseThrow(() -> new NoSuchElementException("해당 게시글을 찾을 수 없습니다. : " + query.id()));
    // 비동기로 변경
    var attachments = postAttachmentQueryService.getAllActive(postId);
    var replies = replyQueryService.getAllActive(new GetRepliesQuery(TargetType.POST, postId));

    return PostDetail.of(post, attachments, replies);
  }

  public PageResponse<PostSummary> page(Pageable pageable) {
    Page<Post> page = postRepository.findAll(pageable);
    List<PostSummary> content = page.getContent().stream()
        .map(post -> new PostSummary(post.getId(), post.getTitle(), post.getAuthor(), post.getCreatedAt(), post.getUpdatedAt()))
        .toList();
    return new PageResponse<>(content, page);
  }
}
