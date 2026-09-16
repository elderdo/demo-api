package com.elderdo.demo_api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Base prefix route path
@CrossOrigin(origins = "http://localhost:4200") // Maps to your Angular app's CLI port mapping
public abstract class BaseApiController {
}
