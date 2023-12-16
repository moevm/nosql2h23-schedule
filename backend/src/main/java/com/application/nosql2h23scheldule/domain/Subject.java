package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    private UUID id;
    private String subjectTitle;
    private String shortSubjectTitle;
    private int scoreUnits;
    private int hours;
}
