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
public class LogChain {
    @MongoId
    private ObjectId id;
    private String weekDay;
    private String time;
    private Group group;
    private BeforeLog before;
    private AfterLog after;
    private String email;
    private Date date;
}
