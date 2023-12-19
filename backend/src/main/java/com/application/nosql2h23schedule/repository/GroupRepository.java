package com.application.nosql2h23schedule.repository;

import com.application.nosql2h23schedule.domain.Group;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends MongoRepository<Group, ObjectId> {
    List<Group> findAllByFacultyAndCourse(String faculty, int course);
}
