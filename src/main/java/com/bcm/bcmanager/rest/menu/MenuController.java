package com.bcm.bcmanager.rest.menu;

import com.bcm.bcmanager.domain.menu.Menu;
import com.bcm.bcmanager.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/menu")
public class MenuController {

    private final MenuService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenus() {
        return new ResponseEntity<>(service.getMenuList(), HttpStatus.OK);
    }

    @GetMapping(value = "/{mid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenu(@PathVariable String mid) {
        return new ResponseEntity<>(service.getMenu(mid), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMenus(@RequestBody Menu m) {
        service.saveMenu(m);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
