package com.bcm.bcmanager.config;

import com.bcm.bcmanager.domain.category.Category;
import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.domain.menu.Menu;
import com.bcm.bcmanager.domain.pos.PosSalesDto;
import com.bcm.bcmanager.repository.menu.MenuRepository;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PosConfig {
    private final PosService posService;

    @PostConstruct
    public void init() {
        posService.insertMenuToDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class PosService {
        private final EntityManager em;
        private final MenuRepository menuRepository;
        private String filepath = System.getProperty("user.dir");

        public void insertMenuToDB() {
            menuRepository.deleteAll();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> request = new HttpEntity<>(headers);
            RestTemplate rt = new RestTemplate();
            String url = "http://127.0.0.1:8081/pos/menu";
            ResponseEntity<String> response = rt.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class
                    );

            JSONArray jsonArray = new JSONArray(response.getBody());
            for(int i = 0 ; i < jsonArray.length() ; ++i) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("menuName");
                String type = obj.getString("menuType");
                Integer price = obj.getInt("price");

                Menu menu = new Menu();
                MenuImage menuImage = new MenuImage();
                Category category = new Category();
                category.setCategory(type);
                menuImage.setFname(filepath + name + ".jpeg");
                menu.setName(name);
                menu.setPrice(price);
                menu.setDesc(name);
                menu.setCategory(category);
                menu.setImage(menuImage);
                em.persist(menu);
            }
        }
        public void Test() {
            Map<String, Object> params = new HashMap<>();
            params.put("prev", "20220401");
            params.put("next", "20220409");
            System.out.println("params = " + params);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(params, headers);
            System.out.println("request = " + request);
            String url = "http://127.0.0.1:8081/pos/sales/day";
            ResponseEntity<String> res = new RestTemplate().
                    postForEntity(url, params, String.class);
            String body = res.getBody();
        }

    }
}
