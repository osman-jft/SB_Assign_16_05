package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.errors.NotFoundException;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public String getSubjectandTeacherName(String name) {
        Subject subject = subjectRepository.findSubjectByName(name);
        Subject subop = Optional.ofNullable(subject)
                .orElseThrow(()->new NotFoundException("Subject not found "));
        return "Teacher Name : " + subject.getTeacher().getName();
    }
}
