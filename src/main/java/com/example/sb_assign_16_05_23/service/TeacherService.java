package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    ResponseDTO<TeacherDTO> getAllTeachers();
    ResponseDTO<TeacherDTO> setTeachers(TeacherDTO teacherData);

    ResponseDTO<TeacherDTO> setAll(List<TeacherDTO> teacherData);
}

