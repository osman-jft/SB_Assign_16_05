package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    //the fields of DTO should match the entity's field
    Long id;

    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    String studentName;

    @Min(0) @Max(600) @NotNull(message = "Marks are required")
    Double marks;

    Integer studentRank = 0;
}
