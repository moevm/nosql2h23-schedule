package com.application.nosql2h23scheldule.service;

import com.application.nosql2h23scheldule.domain.User;
import com.application.nosql2h23scheldule.dto.HelloWorldDto;
import com.application.nosql2h23scheldule.repository.HelloWorldRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class HelloWorldService {
    private final HelloWorldRepository helloWorldRepository;

    public HelloWorldService(HelloWorldRepository helloWorldRepository) {
        this.helloWorldRepository = helloWorldRepository;
    }
}
