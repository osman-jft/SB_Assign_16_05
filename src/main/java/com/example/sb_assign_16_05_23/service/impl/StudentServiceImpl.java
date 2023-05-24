package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {

        //create a List of Student type & store all db entries into it
        List<Student> students = studentRepository.findAll();

        //using ModelMapper we map each individual member of the list of students to the DTO & cache it to be used
        //by the different layers in the application
        //we are using Java 8 ft stream()
        List<StudentDTO> studentDTOS = students
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());

        //returning the StudentDTO list to StudentController
        return studentDTOS;

        //create impl class also
    }

    @Override
    public List<StudentDTO> getStudentsGreaterThan(double value) {
        //Retrieve values through Student Repository that are greater than a particular value
        List<Student> students = studentRepository.findByMarksGreaterThan(value);

        //Convert Values to StudentDTO
        List<StudentDTO> studentDTOS = students
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
        //Return those particular studentDTOS
        return studentDTOS;
    }
}