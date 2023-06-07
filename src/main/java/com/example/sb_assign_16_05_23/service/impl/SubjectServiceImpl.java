package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    public SubjectServiceImpl(TeacherRepository teacherRepository, SubjectRepository subjectRepository , ModelMapper modelMapper) {
        this.subjectRepository=subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SubjectDTO> getAllSubjects(String teacherName){
        List<SubjectDTO> subjectDTOList=subjectRepository.findByTeacherName(teacherName).stream()
                .map(subject -> modelMapper.map(subject,SubjectDTO.class))
                .toList();
        return subjectDTOList;
    }

    @Override
    public List<SubjectDTO> getSubjectandTeacherName(String name) throws IllegalArgumentException {
        Subject subject = subjectRepository.findSubjectByName(name);
        List<SubjectDTO> subjectDTOList = Collections.singletonList(subject).stream()
                .map(subject1 -> modelMapper.map(subject, SubjectDTO.class)).toList();

        return subjectDTOList;
    }

}
