package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @MongoId
    private ObjectId id;
    private String subjectTitle;
    private String shortSubjectTitle;
    private int scoreUnits;
    private int hours;
}
