package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogStudyPlan {
    @MongoId
    private ObjectId id;
    private String faculty;
    private String email;
    private Date date;
    private String fileTitle;
}
