package org.example.community.domain.file.api;

import lombok.RequiredArgsConstructor;
import org.example.community.domain.file.application.UploadFileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/files/{filename}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(uploadFileService.loadAsResource(filename));
  }
}
