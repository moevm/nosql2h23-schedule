package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spesialization {
    @Id
    private UUID id;
    private String spesializationTitle;
    private String department;
    private List<Subject> subjects;
    private List<Group> groups;
}
