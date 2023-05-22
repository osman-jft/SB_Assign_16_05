package com.example.sb_assign_16_05_23.dto.SubjectResponse;

import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    Long id;

    String name;

    TeacherDTO teacherDTO;
}
