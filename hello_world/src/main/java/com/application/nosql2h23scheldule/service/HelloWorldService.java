package com.application.nosql2h23scheldule.service;

import com.application.nosql2h23scheldule.domain.HelloWorld;
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

    public HelloWorld addHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setId(1);
        helloWorld.setText("Hello world!");
        return helloWorldRepository.save(helloWorld);
    }

    public HelloWorldDto getHelloWorld(String id) {
        Optional<HelloWorld> helloWorld = helloWorldRepository.findById(Integer.valueOf(id));
        if (helloWorld.isPresent()) {
            HelloWorldDto helloWorldDto = new HelloWorldDto();
            helloWorldDto.setId(helloWorld.get().getId());
            helloWorldDto.setText(helloWorld.get().getText());
            return helloWorldDto;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
