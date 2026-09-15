package com.elderdo.demo_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  
    @GetMapping("/hello")
    public String sayHello() {
        return "Spring Boot server is officially up and running!";
    }
}
