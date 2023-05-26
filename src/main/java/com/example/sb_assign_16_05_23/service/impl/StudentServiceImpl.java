package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Qualifier("mapperServiceImpl")
    @Autowired
    MapperServiceImpl mapperService;

    @Override
    public ResponseDTO<?> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return mapperService.getResponseDTO(students, "All Students Retrieved From Database");
    }
}



//dt