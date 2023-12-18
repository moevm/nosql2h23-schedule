package com.application.nosql2h23schedule.dto;

import com.application.nosql2h23schedule.domain.Group;
import com.application.nosql2h23schedule.domain.Subject;
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
    private ObjectId id;
    private String weekDay;
    private String time;
    private String groupNumber;
    private List<Group> flowGroups;
    private String classroom;
    private String teacher;
    private String subjectTitle;
}
