package com.application.nosql2h23schedule.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequest {
    private String faculty;
    private int course;
    private String teacher;
}
