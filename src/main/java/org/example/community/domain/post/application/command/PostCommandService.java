package org.example.community.domain.post.application.command;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.post.application.command.dto.CreatePostCommand;
import org.example.community.domain.post.application.command.dto.UpdatePostCommand;
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
  private final PostAttachmentService postAttachmentService;

  @Transactional
  public void create(CreatePostCommand command) {
    var currentMember = command.currentMember();
    var post = Post.builder()
        .title(command.title())
        .content(command.content())
        .author(new Author(currentMember.getId(), currentMember.getNickname()))
        .build();

    postRepository.save(post);
    postAttachmentService.create(post, command.attachmentIds());
  }

  @Transactional
  public void update(UpdatePostCommand command) {
    var currentMember = command.currentMember();
    var post = postRepository.findById(command.id()).orElseThrow(() -> new NoSuchElementException("해당 게시글을 찾을 수 없습니다. : " + command.id()));
    validateAuthor(currentMember, post.getAuthor());

    post.update(command.title(), command.content());
    postAttachmentService.update(post, command.fileIds());
  }

  private void validateAuthor(MemberPrinciple currentMember, Author author) {
    if (!currentMember.getId().equals(author.id())) {
      throw new AccessDeniedException("해당 글을 수정할 권한이 없습니다.");
    }
  }
}
