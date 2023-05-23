package com.example.sb_assign_16_05_23.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    Long id;

    String name;

    List<SubjectDTO> subjects;
}