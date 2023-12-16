package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @UUID
    private UUID id;
    private String fullName;
    private String role;
    private String email;
    private String password;
    private String department;
    private List<Wish> wishes;
    private List<Subject> subjects;
    private List<WorkingHour> workingHours;
}
