package org.example.community.domain.reply.application.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.community.domain.reply.domain.ReplyCount;
import org.example.community.domain.reply.domain.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReplyQueryService {
  private final ReplyRepository replyRepository;

  public List<ReplyDetail> getAllActive(GetRepliesQuery query) {
    return replyRepository.findAllActiveByTarget(query.targetType(), query.targetId()).stream()
        .map(ReplyDetail::of)
        .toList();
  }

  public List<ReplyCount> countAll(GetAllReplyCountQuery query) {
    return replyRepository.countAllByTarget(query.targetType(), query.targetIds());
  }
}
