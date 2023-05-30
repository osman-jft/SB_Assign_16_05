package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    ResponseDTO<?> getAllTeachers();
    ResponseDTO<?> setTeachers(TeacherDTO teacherData);

    ResponseDTO<?> setAll(List<TeacherDTO> teacherData);
}

