package com.bcm.bcmanager.rest.fileio;

import com.bcm.bcmanager.service.fileio.FileIOService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest/file")
public class FileIOController {

    private final FileIOService service;

    @Value("${spring.servlet.multipart.location}")
    private String filepath;

    @Operation(summary = "파일 업로드", description = "Form 제출을 통해 파일을 업로드 합니다.")
    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        Long fid = service.uploadFile(file);

        return new ResponseEntity<>(fid, HttpStatus.OK);
    }

    @Operation(summary = "파일 다운로드")
    @GetMapping(path = "/download/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
//        try {
//            return service.downloadFile(mi.getFname());
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        Path path = Paths.get(filepath + "/" + filename);
        InputStream in = Files.newInputStream(path);
        response.setContentType(Files.probeContentType(Paths.get(filepath + "/" + filename)));
        IOUtils.copy(in, response.getOutputStream());
    }
}
