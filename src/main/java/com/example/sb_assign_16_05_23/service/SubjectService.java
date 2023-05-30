package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> getAllSubject();
    List<SubjectDTO> getSubjectandTeacherName (String name);
}
