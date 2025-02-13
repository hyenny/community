package org.example.community.domain.post.application.command;

import org.example.community.AbstractIntegrationTest;
import org.example.community.domain.auth.domain.Member;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.auth.domain.Role;
import org.example.community.domain.auth.domain.RoleType;
import org.example.community.domain.file.domain.CreatedBy;
import org.example.community.domain.file.domain.UploadFile;
import org.example.community.domain.post.domain.Attachment;
import org.example.community.domain.post.domain.AttachmentStatus;
import org.example.community.domain.post.domain.Post;
import org.example.community.domain.post.domain.PostAttachment;
import org.example.community.domain.post.domain.PostAttachmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@Transactional
class PostAttachmentServiceTest extends AbstractIntegrationTest {

    @Autowired
    private PostAttachmentRepository postAttachmentRepository;

    @Autowired
    private PostAttachmentService postAttachmentService;

    @DisplayName("게시글 첨부파일 삭제시 상태는 DELETED 로 변경")
    @Test
    void delete() {
        // given
        var role = find(Role.create(RoleType.ROLE_USER).getClass(), 2L);
        var user = Member.create("user", "user@test.com", "1234", "user", List.of(role));
        persist(user);

        var currentMember = new MemberPrinciple(user);
        var uploadFile = persist(
                UploadFile.builder()
                        .originalFilename("hi.png")
                        .storeFilename("hi.png")
                        .createdBy(CreatedBy.of(currentMember))
                        .build()
        );
        var post = persist(Post.create("제목", "내용", currentMember));
        PostAttachment attachment = new PostAttachment(post, new Attachment(uploadFile.getId(), uploadFile.getOriginalFilename(), uploadFile.getStoreFilename()));
        persist(attachment);

        // when
        postAttachmentService.delete(post);

        // then
        var actualAttachments = postAttachmentRepository.findByPostId(post.getId());

        then(actualAttachments).hasSize(1);
        then(actualAttachments).allSatisfy(actualAttachment ->
                then(actualAttachment.getStatus()).isEqualTo(AttachmentStatus.DELETED)
        );
    }
}