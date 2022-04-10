package com.bcm.bcmanager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestPos {
    private final PosConfig.PosService posService;

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        posService.Test();
        return "OK";
    }
}
