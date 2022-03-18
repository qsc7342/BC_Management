package com.bcm.bcmanager.rest.menu;

import com.bcm.bcmanager.domain.menu.Menu;
import com.bcm.bcmanager.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/menu")
public class MenuController {

    private final MenuRepository menuRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenus() {
        List<Menu> ml = menuRepository.findAll();
        if (ml == null) {
            ml = new ArrayList();
        }
        return new ResponseEntity<>(ml, HttpStatus.OK);
    }
}
