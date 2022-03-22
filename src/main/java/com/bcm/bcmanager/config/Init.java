package com.bcm.bcmanager.config;

import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.domain.menu.Menu;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.lang.model.SourceVersion;
import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class Init {
    private final InitService initService;


    @PostConstruct
    public void init() throws IOException, ParseException {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        @Value("${spring.servlet.multipart.location}")
        private String filepath;
        public void dbInit() throws IOException, ParseException {
            Reader reader = new FileReader(filepath + "/test.json");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONArray jsonArr = (JSONArray)obj;
            if(jsonArr.size() > 0) {
                for(int i = 0 ; i < jsonArr.size() ; i++) {
                    createMenu(jsonArr, i);
                }
            }
        }

        private void createMenu(JSONArray jsonArr, int i) {
            JSONObject jsonObj = (JSONObject) jsonArr.get(i);
            Menu menu = new Menu();
            MenuImage menuImage = new MenuImage();
            String menuName = (String) jsonObj.get("name");
            Integer menuPrice = Integer.parseInt(String.valueOf(jsonObj.get("price")));
            String menuDescription = (String) jsonObj.get("desc");
            String menuImageName = (String) filepath + jsonObj.get("image");

            menu.setName(menuName);
            menu.setPrice(menuPrice);
            menu.setDesc(menuDescription);
//            menuImage.setFname(menuImageName);
//            menu.setImage(menuImage);
            em.persist(menu);
        }


    }
}
