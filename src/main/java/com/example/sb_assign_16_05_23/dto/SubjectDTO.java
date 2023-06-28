package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    private Long id;
    private String name;
    private Date fromDate;
    private Date toDate;
    private String teacherName;

}
