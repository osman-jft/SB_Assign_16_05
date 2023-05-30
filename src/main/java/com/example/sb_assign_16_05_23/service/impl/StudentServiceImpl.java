package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    List<StudentDTO> students;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<StudentDTO> getAllStudents() {

        students = studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class)).toList();
        return students;
    }

    @Override
    public List<StudentDTO> findByMarksGreaterThan(Double value) {
        students = studentRepository.findByMarksGreaterThan(value).stream()
                .map(student -> modelMapper.map(student,StudentDTO.class))
                .toList();
        return students;
    }


}






