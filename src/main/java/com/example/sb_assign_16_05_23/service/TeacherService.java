package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> getAllTeachers();
    List<TeacherDTO> setTeachers(TeacherDTO teacherData);

    List<Teacher> setAll(List<TeacherDTO> teacherData);
}

