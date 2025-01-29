package org.example.community.domain.post.application.query;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.post.domain.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostQueryService {
  private final PostRepository postRepository;

  public Post get(GetPostQuery query) {
    return postRepository.findById(query.id()).orElseThrow(() -> new NoSuchElementException("해당 게시글을 찾을 수 없습니다. : " + query.id()));
  }
}
