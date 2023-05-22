package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;

import java.util.List;

public interface SubjectService {

    SubjectDTO getSubjectandTeacherName (String name);

    List<SubjectDTO> getAllSubject();
}
