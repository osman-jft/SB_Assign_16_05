package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    //student DTO (Data Transfer Object) to transfer data from DB between different layers of the application
    //namely controller, service, entity, etc.

    //the fields of DTO match the Entity; "Student" in this case
    Long id;

    @NotNull(message = "Name can not be null")
            @NotBlank(message = "Name can not be blank")
    String studentName;

    @Min(0) @Max(600) @NotNull(message = "Marks are required")
    Double marks;

    Integer studentRank = 0;
}
