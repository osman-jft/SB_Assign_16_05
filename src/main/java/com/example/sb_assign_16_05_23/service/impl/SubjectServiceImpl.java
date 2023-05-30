package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {



    private final SubjectRepository subjectRepository;

    private final ModelMapper modelMapper;

    private final ResponseDTO responseDTO;

    public SubjectServiceImpl(SubjectRepository subjectRepository, ModelMapper modelMapper, ResponseDTO responseDTO) {

        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
        this.responseDTO = responseDTO;
    }

    public  ResponseDTO getAllSubject()
    {
        List<Subject> subjects = subjectRepository.findAll();

        return responseDTO.getResponseDTO(subjects, "All Subjects Retrieved From Database");

    }
    @Override
    public ResponseDTO getSubjectandTeacherName(String name) {

        Subject subject = subjectRepository.findSubjectByName(name);


        SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
        subjectDTO.setTeacherName(subject.getTeacher().getName());

        return responseDTO.getResponseDTO(Collections.singletonList(subjectDTO),"Subject retrieved from Database");
    }


}
