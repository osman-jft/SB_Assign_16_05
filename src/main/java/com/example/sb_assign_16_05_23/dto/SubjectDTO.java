package com.example.sb_assign_16_05_23.dto;



import com.example.sb_assign_16_05_23.util.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    String name;

    String teacherName;

}
