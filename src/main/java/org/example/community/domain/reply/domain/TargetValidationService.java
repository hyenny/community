package org.example.community.domain.reply.domain;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.post.domain.PostRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TargetValidationService {
  private final PostRepository postRepository;

  public void validate(TargetType targetType, UUID targetId) {
    if (Objects.requireNonNull(targetType) == TargetType.POST) {
      if (!postRepository.existsById(targetId)) {
        throw new NoSuchElementException("해당 게시글이 존재하지 않습니다. : " + targetId);
      }
    } else {
      throw new IllegalArgumentException("잘못된 TargetType 입니다.");
    }
  }
}
