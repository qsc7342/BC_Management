package com.bcm.bcmanager.config;

import com.bcm.bcmanager.domain.category.Category;
import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.domain.menu.Menu;
import com.bcm.bcmanager.domain.pos.PosSalesDto;
import com.bcm.bcmanager.repository.category.CategoryReposittory;
import com.bcm.bcmanager.repository.menu.MenuRepository;
import com.bcm.bcmanager.repository.menuimage.MenuImageRepository;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.*;
import java.io.IOException;
import java.util.*;


@Controller
@RequiredArgsConstructor
public class PosConfig {
    private final PosService posService;
    @Value("${spring.servlet.multipart.location}")
    private String filepath;
    @GetMapping("/get/posdata")
    public ResponseEntity<?> init() {
        try {
            posService.insertCategoryToDB();
            posService.insertMenuToDB();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class PosService {
        private final EntityManager em;
        private final MenuRepository menuRepository;
        private final CategoryReposittory categoryRepository;
        private final MenuImageRepository menuImageRepository;
        private String filepath = System.getProperty("user.dir");
        @Value("${spring.posurl}")
        String posUrl;
        public void insertCategoryToDB() {
            categoryRepository.deleteAll();

            ResponseEntity<String> response = httpGetFromPos(
                    posUrl + "/pos/menu/type"
            );
            String res = response.getBody();
            List<String> categories = new ArrayList<>();
            boolean flag = false;
            String cur = "";
            for(char c : res.toCharArray()) {
                if(c == '"') {
                    if(flag) {
                        flag = false;
                        categories.add(cur);
                        cur = "";
                    }
                    else {
                        flag = true;
                    }
                }
                else if(flag) {
                    cur = cur + c;
                }
            }
            for(String s : categories) {
                Category category = new Category();
                category.setCategory(s);
                em.persist(category);
            }
        }

        public void insertMenuToDB() {
            menuRepository.deleteAll();
            menuImageRepository.deleteAll();
            ResponseEntity<String> response = httpGetFromPos(
                    posUrl + "/pos/menu"
            );

            JSONArray jsonArray = new JSONArray(response.getBody());
            List<Category> categories = categoryRepository.findAll();
            for(int i = 0 ; i < jsonArray.length() ; ++i) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("menuName");
                String type = obj.getString("menuType");
                Integer price = obj.getInt("price");

                Menu menu = new Menu();
                MenuImage menuImage = new MenuImage();
//                menuImage.setFname("C:/tmp/images/" + name + ".jpeg");
                menuImage.setFname(filepath +  "/images/" + name + ".jpeg");
                menu.setName(name);
                menu.setPrice(price);
                menu.setDesc(name);
                for(Category category : categories) {
                    if(category.getCategory().equals(type)) {
                        menu.setCategory(category);
                    }
                }
                menu.setImage(menuImage);
                em.persist(menu);
            }
        }
    }

    private static ResponseEntity<String> httpGetFromPos(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
                );
        return response;
    }
}
