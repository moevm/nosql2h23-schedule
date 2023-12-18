package com.application.nosql2h23schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
    private ObjectId id;
    private ObjectId idAdmin;
    private ObjectId idChain;
    private String wishText;
    private Date date;
    private String status;
}
