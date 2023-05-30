package com.example.sb_assign_16_05_23.service;


import com.example.sb_assign_16_05_23.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {


      List<TeacherDTO> getAllTeachers();
      List<TeacherDTO> setTeachers(TeacherDTO teacherData);
      List<TeacherDTO> setAll(List<TeacherDTO> teacherData);

}

