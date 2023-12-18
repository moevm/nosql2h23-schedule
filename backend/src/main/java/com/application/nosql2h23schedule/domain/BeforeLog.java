package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeforeLog {
    private String beforeSubject;
    private int beforeClassroom;
    private String beforeTeacher;
}
