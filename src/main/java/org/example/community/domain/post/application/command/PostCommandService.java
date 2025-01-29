package org.example.community.domain.post.application.command;

import lombok.RequiredArgsConstructor;
import org.example.community.domain.post.application.query.GetPostQuery;
import org.example.community.domain.post.application.query.PostQueryService;
import org.example.community.domain.post.domain.Author;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.post.domain.PostRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostCommandService {
  private final PostRepository postRepository;
  private final PostQueryService postQueryService;

  @Transactional
  public void create(CreatePostCommand command) {
    var currentMember = command.currentMember();
    var post = Post.builder()
        .title(command.title())
        .content(command.content())
        .author(new Author(currentMember.getId(), currentMember.getNickname()))
        .build();
    postRepository.save(post);
  }

  @Transactional
  public void update(UpdatePostCommand command) {
    var currentMember = command.currentMember();
    var post = postQueryService.get(new GetPostQuery(command.id()));
    if (!currentMember.getId().equals(post.getAuthor().id())) {
      throw new AccessDeniedException("해당 글을 수정할 권한이 없습니다.");
    }

    post.update(command.title(), command.content());
  }
}
