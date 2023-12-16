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
public class LogStudyPlan {
    @Id
    private UUID id;
    private String faculty;
    private String email;
    private Date date;
    private String fileTitle;
}
