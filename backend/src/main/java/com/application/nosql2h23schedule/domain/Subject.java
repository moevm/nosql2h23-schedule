package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private String subjectTitle;
    private String shortSubjectTitle;
    private int scoreUnits;
    private int hours;
}
