package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.dto.SubjectListDTO;

import java.util.List;

public interface SubjectService {
  
    List<SubjectDTO> getAllSubjectsByTeacherAndFromDateAndToDate(SubjectListDTO subjectListDTO);
    public String getSubjectandTeacherName(String name);
}
