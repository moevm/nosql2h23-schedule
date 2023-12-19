package com.application.nosql2h23schedule.dto;

import com.application.nosql2h23schedule.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

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
