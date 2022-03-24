package com.bcm.bcmanager.service.menu;

import com.bcm.bcmanager.domain.menu.Menu;
import com.bcm.bcmanager.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository repo;

    public List<Menu> getMenuList() {
        List<Menu> ml = repo.findAll();

        if (ml == null) {
            ml = new ArrayList();
        }

        return ml;
    }

    public void saveMenu(Menu m) {
        repo.save(m);
    }

    public Menu getMenu(String mid) {
        return repo.getById(Long.parseLong(mid));
    }

}
