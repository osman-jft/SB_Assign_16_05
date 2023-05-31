package com.example.sb_assign_16_05_23.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    Long id;
    String name;
    String teacherName;
    //TeacherDTO teacherDTO;
}
