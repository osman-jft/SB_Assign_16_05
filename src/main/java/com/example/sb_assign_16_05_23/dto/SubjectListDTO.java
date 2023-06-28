package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectListDTO {
    @Min(value = 0)
    @NotNull(message="page no. can't be null")
    Integer pageNo;
    @Min(value = 0)
    @NotNull(message="record no. can't be null")
    Integer noOfRecords;
    String teacherName;
    Long fromDate;
    Long toDate;
}
