package com.example.stock.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/api/test/hello")
    public String hello() {
        log.info("call hello()");
        return "Hello Boot + Nuxt";
    }

}
