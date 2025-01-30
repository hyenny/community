package org.example.community.domain.post.application.command;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
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
    var post = postRepository.findById(command.id()).orElseThrow(() -> new NoSuchElementException("해당 게시글을 찾을 수 없습니다. : " + command.id()));
    if (!currentMember.getId().equals(post.getAuthor().id())) {
      throw new AccessDeniedException("해당 글을 수정할 권한이 없습니다.");
    }

    post.update(command.title(), command.content());
  }
}
