package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;

import java.util.List;

public interface StudentService {


    List<StudentDTO> getAllStudents();

    List<StudentDTO> findByMarksGreaterThan(Double value);

    List<Student> calculateRank(List<StudentDTO> studentDto);

    List<StudentDTO> registerStudentList(List<StudentDTO> studentDtos);


}

