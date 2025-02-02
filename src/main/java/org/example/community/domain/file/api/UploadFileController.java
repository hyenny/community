package org.example.community.domain.file.api;

import lombok.RequiredArgsConstructor;
import org.example.community.domain.auth.domain.MemberPrinciple;
import org.example.community.domain.file.api.docs.UploadFileDocument;
import org.example.community.domain.file.application.UploadFileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class UploadFileController implements UploadFileDocument {

  private final UploadFileService uploadFileService;

  @Override
  @PostMapping(value = "/api/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal MemberPrinciple currentMember) {
    return ResponseEntity.ok(new UploadFileResponse(uploadFileService.save(file, currentMember)));
  }

  @Override
  @GetMapping("/files/{filename}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(uploadFileService.loadAsResource(filename));
  }
}
