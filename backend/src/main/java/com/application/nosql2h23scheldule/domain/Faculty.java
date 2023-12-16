package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    private UUID id;
    private String facultyName;
    private List<Spesialization> spesializations;
}
