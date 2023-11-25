package com.example.stock.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/api/test/hello")
    public String hello() {
        log.info("call hello()");
        return "Hello Boot + Nuxt";
    }

}
