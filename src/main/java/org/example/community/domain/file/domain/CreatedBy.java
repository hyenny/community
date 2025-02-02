package org.example.community.domain.file.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import org.example.community.domain.auth.domain.MemberPrinciple;

@Embeddable
public record CreatedBy(

    @Column(name = "created_by_id", nullable = false)
    UUID id,

    @Column(name = "created_by_name", nullable = false)
    String name
) {

    public static CreatedBy of(MemberPrinciple member) {
        return new CreatedBy(member.getId(), member.getUsername());
    }
}
