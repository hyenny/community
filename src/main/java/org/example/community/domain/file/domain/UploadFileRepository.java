package org.example.community.domain.file.domain;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, UUID> {
}
