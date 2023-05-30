package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final ResponseDTO responseDTO;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper, ResponseDTO responseDTO) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.responseDTO = responseDTO;
    }

    @Override
    public ResponseDTO getAllStudents() {
        List<Student> students = studentRepository.findAll();

        //System.out.println(responseDTO.getResponseDTO(students, "All Students Retrieved From Database").toString());

        return responseDTO.getResponseDTO(students, "All Students Retrieved From Database");
    }


    @Override
    public ResponseDTO getStudentsGreaterThan(double value) {
        //Retrieve values through Student Repository that are greater than a particular value
        List<Student> students = studentRepository.findByMarksGreaterThan(value);

        //Convert Values to StudentDTO
        List<StudentDTO> studentDTOS = students
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
        //Return those particular studentDTOS

        return responseDTO.getResponseDTO(studentDTOS, "List of Students retrieved from Database");
    }
}






