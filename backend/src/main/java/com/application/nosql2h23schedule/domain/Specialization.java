package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialization {
    @MongoId
    private ObjectId id;
    private String specializationTitle;
    private String department;
    private List<Subject> subjects;
    private List<Group> groups;
}
