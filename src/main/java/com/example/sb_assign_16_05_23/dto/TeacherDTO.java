package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    Long id;



    @NotBlank(message = "Name can not be blank")
    @NotNull(message = "Name can not be null")
    String name;

    @Valid
    List<SubjectDTO> subjects;
}