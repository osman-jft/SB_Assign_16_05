package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    Long id;

    @NotBlank(message = "Name can not be blank")
    String name;
}
