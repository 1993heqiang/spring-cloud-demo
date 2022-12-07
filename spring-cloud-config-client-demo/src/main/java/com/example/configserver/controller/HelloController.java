package com.example.configserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${foo}")
    String name = "World";

    @GetMapping("/hello")
    public String hello() {
        return "Hello " + name;
    }
}
