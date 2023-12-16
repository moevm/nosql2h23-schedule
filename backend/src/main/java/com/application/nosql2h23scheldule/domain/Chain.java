package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chain {
    @UUID
    private UUID id;
    private String weekDay;
    private String time;
    private Group group;
    private List<Group> flowGroups;
    private String classroom;
    private String teacher;
    private Subject subject;
}
