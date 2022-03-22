package com.bcm.bcmanager.rest.origin;

import com.bcm.bcmanager.domain.origin.Origin;
import com.bcm.bcmanager.service.origin.OriginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/origin")
public class OriginController {

    private final OriginService service;

    @GetMapping
    public ResponseEntity<?> getOrigins() {
        return new ResponseEntity<>(service.getOrigins(), HttpStatus.OK);
    }

    @GetMapping(value = "/{oid}")
    public ResponseEntity<?> getOrigin(@PathVariable String oid) {
        return new ResponseEntity<>(service.getOrigin(oid), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrigin(@RequestBody Origin origin) {
        return new ResponseEntity<>(service.saveOrigin(origin), HttpStatus.OK);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOrigin(@RequestBody Origin origin) {
        try {
            service.deleteOrigin(origin);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
