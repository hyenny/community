package org.example.community.domain.post.application.command;

import lombok.RequiredArgsConstructor;
import org.example.community.domain.post.domain.Author;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.post.domain.PostRepository;
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
}
