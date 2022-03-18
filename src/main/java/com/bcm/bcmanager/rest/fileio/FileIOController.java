package com.bcm.bcmanager.rest.fileio;

import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.service.fileio.FileIOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(path = "/rest/file")
public class FileIOController {

    @Autowired
    FileIOService service;

    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        Long fid = service.uploadFile(file);

        return new ResponseEntity<>(fid, HttpStatus.OK);
    }

    @PostMapping(path = "/download")
    public ResponseEntity<?> downloadFile(@RequestBody MenuImage mi) {
        try {
            return service.downloadFile(mi.getFname());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
