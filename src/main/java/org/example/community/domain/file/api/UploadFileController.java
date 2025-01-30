package org.example.community.domain.file.api;

import lombok.RequiredArgsConstructor;
import org.example.community.domain.file.application.UploadFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class UploadFileController {

  private final UploadFileService uploadFileService;

  @PostMapping("/api/upload-file")
  public ResponseEntity<UploadFileResponse> uploadFiles(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok(new UploadFileResponse(uploadFileService.save(file)));
  }
}
