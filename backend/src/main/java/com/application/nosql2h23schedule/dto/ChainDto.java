package com.application.nosql2h23schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChainDto {
    private String id;
    private String classroom;
    private String teacher;
    private String subjectTitle;
}
