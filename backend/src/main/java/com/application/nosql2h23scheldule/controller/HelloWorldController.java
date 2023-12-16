package com.application.nosql2h23scheldule.controller;

import com.application.nosql2h23scheldule.domain.User;
import com.application.nosql2h23scheldule.dto.HelloWorldDto;
import com.application.nosql2h23scheldule.service.HelloWorldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

}
