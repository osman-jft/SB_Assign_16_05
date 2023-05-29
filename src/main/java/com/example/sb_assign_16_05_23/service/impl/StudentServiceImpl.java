package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{


   private final StudentRepository studentRepository;


    private final ResponseDTO responseDTO;

    public StudentServiceImpl(StudentRepository studentRepository, ResponseDTO responseDTO) {
        this.studentRepository = studentRepository;
        this.responseDTO = responseDTO;
    }

    @Override
    public ResponseDTO getAllStudents() {
        List<Student> students = studentRepository.findAll();

        //System.out.println(responseDTO.getResponseDTO(students, "All Students Retrieved From Database").toString());

        return responseDTO.getResponseDTO(students, "All Students Retrieved From Database");
    }
}



//dt