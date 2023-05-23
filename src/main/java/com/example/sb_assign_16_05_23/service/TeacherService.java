package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> getAllTeachers();
    ResponseEntity<String> setTeachers(TeacherDTO teacherData);

    ResponseEntity<String> setAll(List<TeacherDTO> teacherData);
}

