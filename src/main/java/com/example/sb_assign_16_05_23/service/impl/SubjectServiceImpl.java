package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;


    @Autowired
    ResponseDTO<?> responseDTO;

    @Autowired
    ModelMapper mapper;
    @Override
    public List<SubjectDTO> getSubjectandTeacherName(String name) {
        return Collections.singleton(subjectRepository.findSubjectByName(name)).stream()
                .map(subject -> getSubjectTeacherName(subject)).toList();
    }

    @Override
    public List<SubjectDTO> getAllSubject() {

        return subjectRepository.findAll()
                .stream().map(subject -> mapper.map(subject, SubjectDTO.class)).toList();

    }

    public SubjectDTO getSubjectTeacherName(Subject subject)
    {
        Teacher teacher = subject.getTeacher();
        SubjectDTO subjectDTO = mapper.map(subject, SubjectDTO.class);
        subjectDTO.setTeachername(teacher.getName());
        return subjectDTO;
    }

}
