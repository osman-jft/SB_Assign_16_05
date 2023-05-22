package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;

import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;

import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {


    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Qualifier("getModelMapper")
    @Autowired
    ModelMapper modelMapper;

    public  List<SubjectDTO> getAllSubject()
    {
        List<Subject> subjects = subjectRepository.findAll();

        return subjects.
                stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());

    }
    @Override
    public SubjectDTO getSubjectandTeacherName(String name) {

        Subject subject = subjectRepository.findSubjectByName(name);


        SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
        /*TeacherDTO teacherDTO = modelMapper.map(subject.getTeacher(),TeacherDTO.class);
        subjectDTO.setTeacherDTO(teacherDTO);*/
        subjectDTO.setTeacherName(subject.getTeacher().getName());

        return subjectDTO;
    }


}
