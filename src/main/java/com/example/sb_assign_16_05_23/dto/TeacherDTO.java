package com.example.sb_assign_16_05_23.dto;

import com.example.sb_assign_16_05_23.entity.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonView(Views.TeacherView.class)
public class TeacherDTO {

    Long id;

    String name;

    List<SubjectDTO> subjects;
}