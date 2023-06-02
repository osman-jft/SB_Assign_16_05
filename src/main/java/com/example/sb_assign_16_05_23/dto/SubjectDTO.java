package com.example.sb_assign_16_05_23.dto;
import com.example.sb_assign_16_05_23.entity.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SubjectDTO {

    @JsonView(Views.TeacherView.class)
    Long id;
    @JsonView(Views.TeacherView.class)
    String name;

    String teacherName;
}
