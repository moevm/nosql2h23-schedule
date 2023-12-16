package com.application.nosql2h23scheldule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
    @Id
    private UUID id;
    private UUID idAdmin;
    private UUID idChain;
    private String wishText;
    private Date date;
    private String status;
}
