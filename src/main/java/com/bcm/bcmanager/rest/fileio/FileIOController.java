package com.bcm.bcmanager.rest.fileio;

import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.service.fileio.FileIOService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest/file")
public class FileIOController {

    private final FileIOService service;

    @Operation(summary = "파일 업로드", description = "Form 제출을 통해 파일을 업로드 합니다.")
    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        Long fid = service.uploadFile(file);

        return new ResponseEntity<>(fid, HttpStatus.OK);
    }

    @Operation(summary = "파일 다운로드")
    @PostMapping(path = "/download")
    public ResponseEntity<?> downloadFile(@RequestBody MenuImage mi) {
        try {
            return service.downloadFile(mi.getFname());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
