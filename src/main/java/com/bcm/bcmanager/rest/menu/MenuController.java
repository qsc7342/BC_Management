package com.bcm.bcmanager.rest.menu;

import com.bcm.bcmanager.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/menu")
public class MenuController {

    private final MenuRepository menuRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenus() {
        return ResponseEntity.ok(menuRepository.findAll());
    }
}
