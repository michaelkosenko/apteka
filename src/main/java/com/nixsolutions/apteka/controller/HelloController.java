package com.nixsolutions.apteka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello() {
        log.debug("Yo!");
        return "index";
    }
}
