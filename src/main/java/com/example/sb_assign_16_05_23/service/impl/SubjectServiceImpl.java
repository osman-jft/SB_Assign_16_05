package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
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
    ModelMapper mapper;

    private List<SubjectDTO> subjectDtoList;

    @Override
    public List<SubjectDTO> getSubjectandTeacherName(String name) {
        Subject subject = subjectRepository.findSubjectByName(name);
        if (subject != null)
            return subjectDtoList = Collections.singleton(subject).stream()
                    .map(sub -> getSubjectTeacherName(sub))
                    .toList();
        else
            return null;
    }

    @Override
    public List<SubjectDTO> getAllSubject() {

        subjectDtoList = subjectRepository.findAll().stream()
                .map(subject -> mapper.map(subject, SubjectDTO.class))
                .toList();
        return subjectDtoList;
    }

    public SubjectDTO getSubjectTeacherName(Subject subject) {
        Teacher teacher = subject.getTeacher();
        SubjectDTO subjectDTO = mapper.map(subject, SubjectDTO.class);
        subjectDTO.setTeacherName(teacher.getName());
        return subjectDTO;
    }


}
