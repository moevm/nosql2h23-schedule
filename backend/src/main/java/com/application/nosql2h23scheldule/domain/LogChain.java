package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogChain {
    @Id
    private UUID id;
    private String weekDay;
    private String time;
    private Group group;
    private BeforeLog before;
    private AfterLog after;
    private String email;
    private Date date;
}
