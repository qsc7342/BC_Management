package com.bcm.bcmanager.config;

import com.bcm.bcmanager.domain.pos.PosSalesDto;
import lombok.RequiredArgsConstructor;
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
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Profile(value = "testDB")
@Component
@RequiredArgsConstructor
public class PosConfig {
    private final PosService posService;

    @PostConstruct
    public void init() {
        posService.Test();
    }

    @Component
//    @Transactional
//    @RequiredArgsConstructor
    static class PosService {

        public void Test() {
//            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//            params.add("prev", "20220401");
//            params.add("next", "20220409");

//            JSONObject params = new JSONObject();

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
            System.out.println("res.getBody() = " + res.getBody());
            System.out.println("res.getStatusCode() = " + res.getStatusCode());
        }

    }
}
