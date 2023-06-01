package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    //the fields of DTO should match the entity's field
    Long id;

    @NotBlank(message = "Name can not be blank")
    String studentName;

    @NotNull(message = "Marks are required")
    @Range(min = 0, max = 600, message = "Marks must be between 0 to 600")
    Double marks;

    Integer studentRank = 0;
}
