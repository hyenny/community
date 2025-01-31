package org.example.community.domain.post.application.query;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.community.common.page.PageResponse;
import org.example.community.domain.post.application.query.dto.GetPostQuery;
import org.example.community.domain.post.application.query.dto.PostDetail;
import org.example.community.domain.post.application.query.dto.PostSummary;
import org.example.community.domain.post.domain.AttachmentCount;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.post.domain.PostRepository;
import org.example.community.domain.reply.application.query.GetAllReplyCountQuery;
import org.example.community.domain.reply.application.query.GetRepliesQuery;
import org.example.community.domain.reply.application.query.ReplyQueryService;
import org.example.community.domain.reply.domain.ReplyCount;
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
    Page<Post> page = postRepository.findAllOrderByCreatedAtDesc(pageable);
    var postIds = page.getContent().stream().map(Post::getId).toList();

    var postAttachmentCount = postAttachmentQueryService.countAll(postIds).stream()
        .collect(Collectors.toMap(AttachmentCount::postId, AttachmentCount::count));
    var replyCount = replyQueryService.countAll(new GetAllReplyCountQuery(TargetType.POST, postIds)).stream()
        .collect(Collectors.toMap(ReplyCount::targetId, ReplyCount::count));

    List<PostSummary> content = page.getContent().stream()
        .map(post -> PostSummary.of(post, postAttachmentCount.getOrDefault(post.getId(), 0L), replyCount.getOrDefault(post.getId(), 0L)))
        .toList();
    return new PageResponse<>(content, page);
  }
}
