package com.bcm.bcmanager.service.fileio;

import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.repository.menuimage.MenuImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileIOService {

    private final MenuImageRepository mirepo;

    @Value("${spring.servlet.multipart.location}")
    private String filepath;

    public Long uploadFile(MultipartFile file) {
        if (Files.notExists(Paths.get(filepath))) {
            try {
                Files.createDirectory(Paths.get(filepath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MenuImage mi = new MenuImage();

        String fnm = UUID.randomUUID().toString();

        mi.setFname(fnm + "_" + file.getOriginalFilename());

        mirepo.save(mi);

        File sfile = new File(filepath + "/" + mi.getFname());

        try {
            file.transferTo(sfile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return mi.getId();
    }

    public ResponseEntity<?> downloadFile(String filename) throws IOException {
        Path path = Paths.get(filepath + "/" + filename);
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDisposition(ContentDisposition.builder("attachment")
//                                                        .filename(filename, StandardCharsets.UTF_8)
//                                                        .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);;
        byte[] resource = StreamUtils.copyToByteArray(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
