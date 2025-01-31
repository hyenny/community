package org.example.community.domain.reply.application.command;

import lombok.RequiredArgsConstructor;
import org.example.community.domain.reply.domain.Author;
import org.example.community.domain.reply.domain.Reply;
import org.example.community.domain.reply.domain.ReplyRepository;
import org.example.community.domain.reply.domain.TargetValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyCommandService {
  private final TargetValidationService targetValidationService;
  private final ReplyRepository replyRepository;

  @Transactional
  public void create(CreateReplyCommand command) {
    var currentMember = command.currentMember();
    targetValidationService.validate(command.targetType(), command.targetId());

    var reply = Reply.builder()
        .targetType(command.targetType())
        .targetId(command.targetId())
        .content(command.content())
        .author(new Author(currentMember.getId(), currentMember.getNickname()))
        .build();

    replyRepository.save(reply);
  }
}
