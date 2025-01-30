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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostQueryService {
  private final PostRepository postRepository;

  public PostDetail get(GetPostQuery query) {
    var post = postRepository.findById(query.id()).orElseThrow(() -> new NoSuchElementException("해당 게시글을 찾을 수 없습니다. : " + query.id()));
    return new PostDetail(
        post.getTitle(),
        post.getContent(),
        post.getAuthor(),
        post.getCreatedAt(),
        post.getUpdatedAt()
    );
  }

  public PageResponse<PostSummary> page(Pageable pageable) {
    Page<Post> page = postRepository.findAll(pageable);
    List<PostSummary> content = page.getContent().stream()
        .map(post -> new PostSummary(post.getId(), post.getTitle(), post.getAuthor(), post.getCreatedAt(), post.getUpdatedAt()))
        .toList();
    return new PageResponse<>(content, page);
  }
}
