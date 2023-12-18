package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @MongoId
    private ObjectId id;
    private String email;
    private String password;
    private String fullName;
    private String role;
    private String department;
    private List<Wish> wishes;
    private List<Subject> subjects;
    private List<WorkingHour> workingHours;
}
