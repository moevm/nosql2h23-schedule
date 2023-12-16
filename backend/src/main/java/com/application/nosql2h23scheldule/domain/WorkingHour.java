package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHour {
    private String weekDay;
    private String time;
}
