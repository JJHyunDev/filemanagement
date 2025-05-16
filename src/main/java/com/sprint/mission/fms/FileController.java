package com.sprint.mission.fms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@Slf4j
@RequiredArgsConstructor
public class FileController {

  private final FileService fileService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<FileResponseDto> uploadFile(
      @RequestParam("file") MultipartFile file,
      @RequestParam("description") String description
  ){
    log.info("Received file upload request: filename={}, description={}",
        file.getOriginalFilename(), description);

    FileResponseDto fileResponseDto = fileService.uploadFile(file, description);

    return ResponseEntity.ok(fileResponseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id){
    log.info("Received file retrieval request: id={}", id);

    FileResponseDto response = fileService.getFileById(id);
    return ResponseEntity.ok(response);
  }

}
