package com.bcmng.bcmanager.rest.menu;

import com.bcmng.bcmanager.domain.menu.Menu;
import com.bcmng.bcmanager.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.hateoas.PagedModel;
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
    public ResponseEntity<?> getMenus(@PageableDefault Pageable pageable) {
        Page<Menu> menus = menuRepository.findAll(pageable);
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(pageable.getPageSize(),
                                                                           menus.getNumber(),
                                                                           menus.getTotalElements());
        PagedModel<Menu> model = PagedModel.of(menus.getContent(), pageMetadata);

        return ResponseEntity.ok(model);
    }
}
