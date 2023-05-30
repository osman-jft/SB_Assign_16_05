package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

<<<<<<< HEAD
    List<TeacherDTO> getAllTeachers();
    ResponseDTO<?> setTeachers(TeacherDTO teacherData);
=======
    ResponseDTO getAllTeachers();
    ResponseDTO setTeachers(TeacherDTO teacherData);
>>>>>>> feature-pradeep

    ResponseDTO setAll(List<TeacherDTO> teacherData);
}

