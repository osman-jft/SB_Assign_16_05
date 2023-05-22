package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {


    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Qualifier("getModelMapper")
    @Autowired
    ModelMapper modelMapper;


    @Override
    public SubjectDTO getSubjectandTeacherName(String name) {

        Subject subject = subjectRepository.findSubjectByName("English");

        //System.out.println("Subject id " + subject.getId());

        SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
        subjectDTO.setTeacherName(subject.getTeacher().getName());

        return subjectDTO;
    }


}
