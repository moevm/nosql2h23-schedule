package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chain {
    @MongoId
    private ObjectId id;
    private String weekDay;
    private String time;
    private Group group;
    private List<Group> flowGroups;
    private String classroom;
    private String teacher;
    private Subject subject;
}
