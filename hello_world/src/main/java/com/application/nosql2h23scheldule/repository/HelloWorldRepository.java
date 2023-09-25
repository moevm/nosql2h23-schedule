package com.application.nosql2h23scheldule.repository;

import com.application.nosql2h23scheldule.domain.HelloWorld;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HelloWorldRepository extends MongoRepository<HelloWorld, Integer> {
}
